package gist.pilldispenser.drug.drugInfo.service;

import com.querydsl.core.Tuple;
import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import gist.pilldispenser.drug.api.drugIdentificationAPI.repository.DrugIdentificationRepository;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;
import gist.pilldispenser.drug.api.drugProductAPI.repository.DrugProductRepository;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.api.drugSummaryAPI.repository.DrugSummaryRepository;
import gist.pilldispenser.drug.drugInfo.domain.dto.*;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepository;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepositoryCustomImpl;
import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import gist.pilldispenser.drug.medication.repository.FullMedicationInfoRepository;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.CartridgeSlot;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.CartridgeSlotRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static gist.pilldispenser.drug.drugInfo.domain.DrugInfoMapper.toDrugInfo;

@Service
@RequiredArgsConstructor
public class DrugInfoServiceImpl implements DrugInfoService {

    private final DrugInfoRepository drugInfoRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;
    private final FullMedicationInfoRepository fullMedicationInfoRepository;
    private final DrugIdentificationRepository drugIdentificationRepository;
    private final DrugSummaryRepository drugSummaryRepository;
    private final CartridgeSlotRepository cartridgeSlotRepository;
    private final DrugInfoRepositoryCustomImpl drugInfoRepositoryCustomImpl;

    @Transactional
    public DrugRegistrationResponse createDrugInfoManually(UsersDetails usersDetails,
                                                           DrugManualInfoRequest request) {
        DrugInfo drugInfo = toDrugInfo(request);

        UserDrugInfo userDrugInfo = UserDrugInfo.create(usersDetails.getUsers(), drugInfo, null);
        CartridgeSlot cartridgeSlot = cartridgeSlotRepository.findById(request.getSlotId()).orElseThrow(
                () -> new RuntimeException("카트리지 슬롯이 존재하지 않습니다."));
        cartridgeSlot = cartridgeSlot.toBuilder()
                        .userDrugInfo(userDrugInfo)
                        .isOccupied(true)
                        .size(request.getDiskSize())
                        .build();

        drugInfoRepository.save(drugInfo);
        userDrugInfoRepository.save(userDrugInfo);
        cartridgeSlotRepository.save(cartridgeSlot);

        return DrugRegistrationResponse.builder()
                .drugId(userDrugInfo.getId())
                .drugName(drugInfo.getName())
                .mainIngredient(drugInfo.getMtralNm())
                .dosageInstructions("하루 " + drugInfo.getDailyDosage() + "번 ")
                .shape(drugInfo.getShape().getDescription())
                .size(String.valueOf(drugInfo.getLongAxis()))
                .slotNumber(String.valueOf(cartridgeSlot.getSlotNumber()))
                .slotSize(cartridgeSlot.getSize())
                .build();
    }

    public DrugRegistrationResponse createDrugInfoAutomatically(UsersDetails usersDetails,
                                                                DrugAutoRegistrationRequest request){
        DrugIdentification drugIdentification = drugIdentificationRepository.findByItemSeq(request.getItemSeq())
                .orElseThrow(() -> new RuntimeException("약 성분 정보가 존재하지 않습니다."));
        DrugSummary drugSummary = drugSummaryRepository.findByItemSeq(request.getItemSeq())
                .orElseThrow(()->new RuntimeException("약 혼용 정보가 존재하지 않습니다."));

        FullMedicationInfo fullMedicationInfo = FullMedicationInfo.builder()
                .drugIdentification(drugIdentification)
                .drugSummary(drugSummary)
                .build();

        UserDrugInfo userDrugInfo = UserDrugInfo.builder()
                .user(usersDetails.getUsers())
                .fullMedicationInfo(fullMedicationInfo)
                .build();

        CartridgeSlot assignedSlot = cartridgeSlotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new RuntimeException("슬롯이 존재하지 않습니다."));
        CartridgeSlot updatedSlot = assignedSlot.toBuilder()
                .userDrugInfo(userDrugInfo)
                .size(request.getDiskSize())
                .slotNumber(assignedSlot.getSlotNumber())
                .isOccupied(true)
                .build();

        fullMedicationInfoRepository.save(fullMedicationInfo);
        userDrugInfoRepository.save(userDrugInfo);
        cartridgeSlotRepository.save(updatedSlot);

        Tuple tuple = drugInfoRepositoryCustomImpl.findDrugIdentificationAndSummaryAndProductByItemSeq(
                request.getItemSeq());
        DrugIdentification drugIdentity = tuple.get(0, DrugIdentification.class);
        DrugSummary drugSum = tuple.get(1, DrugSummary.class);
        DrugProduct drugProduct = tuple.get(2, DrugProduct.class);

        return DrugRegistrationResponse.builder()
                .drugId(userDrugInfo.getId())
                .drugName(drugIdentity.getItemName())
                .mainIngredient(drugProduct.getMtralNm())
                .shape(drugIdentity.getDrugShape())
                .dosageInstructions(drugSum.getUseMethodQesitm())
                .size(drugIdentity.getLengLong())
                .slotNumber(String.valueOf(updatedSlot.getSlotNumber()))
                .slotSize(request.getDiskSize())
                .build();
    }
}
