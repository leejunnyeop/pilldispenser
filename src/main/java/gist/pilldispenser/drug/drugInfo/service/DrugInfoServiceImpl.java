package gist.pilldispenser.drug.drugInfo.service;

import com.querydsl.core.Tuple;
import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.drugInfo.domain.DrugInfoMapper;
import gist.pilldispenser.drug.drugInfo.domain.dto.*;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugShape;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepository;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepositoryCustomImpl;
import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import gist.pilldispenser.drug.medication.repository.FullMedicationInfoRepository;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.CartridgeSlot;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.CartridgeSlotRepository;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DrugInfoServiceImpl implements DrugInfoService {

    private final DrugInfoRepository drugInfoRepository;
    private final UsersRepository usersRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;
    private final FullMedicationInfoRepository fullMedicationInfoRepository;
    private final CartridgeSlotRepository cartridgeSlotRepository;
    private final DrugInfoRepositoryCustomImpl drugInfoRepositoryCustomImpl;

    @Transactional
    public DrugRegistrationResponse createDrugInfoManually(Long userId, DrugInfoRequestBase drugInfoRequest) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 userId에 대한 사용자 정보를 찾을 수 없습니다: " + userId));

        DrugInfo drugInfo;

        // DrugInfoRequest의 구체적인 타입에 따라 DrugInfo 객체 생성
        if (drugInfoRequest instanceof RoundDrugInfoRequest) {
            drugInfo = DrugInfoMapper.toDrugInfo((RoundDrugInfoRequest) drugInfoRequest);
        } else if (drugInfoRequest instanceof OvalDrugInfoRequest) {
            drugInfo = DrugInfoMapper.toDrugInfo((OvalDrugInfoRequest) drugInfoRequest);
        } else {
            throw new IllegalArgumentException("알 수 없는 약물 정보 요청 타입입니다.");
        }

        drugInfoRepository.save(drugInfo);

        UserDrugInfo userDrugInfo = UserDrugInfo.create(user, drugInfo, null);
        userDrugInfoRepository.save(userDrugInfo);

        // DrugInfoResponse DTO로 변환
        String shape = drugInfo.getShape().name();
        String size;

        if (drugInfo.getShape() == DrugShape.ROUND) {
            size = "직경 " + drugInfo.getLongAxis() + "mm";
        } else if (drugInfo.getShape() == DrugShape.OVAL) {
            size = "장축 " + drugInfo.getLongAxis() + "mm, 단축 " + drugInfo.getShortAxis() + "mm";
        } else {
            size = "알 수 없음";
        }

        return DrugRegistrationResponse.builder()
                .drugName(drugInfo.getName())
                .dosageInstructions("하루 " + drugInfo.getDailyDosage() + "번 ")
                .shape(shape)
                .size(size)
                .build();
    }

    public DrugRegistrationResponse createDrugInfoAutomatically(UsersDetails usersDetails,
                                                                DrugAutoRegistrationRequest request){
        FullMedicationInfo fullMedicationInfo = fullMedicationInfoRepository.findByItemSeq(request.getItemSeq())
                .orElseThrow(() -> new RuntimeException("약이 존재하지 않습니다."));

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

        userDrugInfoRepository.save(userDrugInfo);
        cartridgeSlotRepository.save(updatedSlot);

        Tuple tuple = drugInfoRepositoryCustomImpl.findDrugIdentificationAndSummaryAndProductByItemSeq(
                request.getItemSeq());
        DrugIdentification drugIdentification = tuple.get(0, DrugIdentification.class);
        DrugSummary drugSummary = tuple.get(1, DrugSummary.class);
        DrugProduct drugProduct = tuple.get(2, DrugProduct.class);

        return DrugRegistrationResponse.builder()
                .drugName(drugIdentification.getItemName())
                .mainIngredient(drugProduct.getMtralNm())
                .shape(drugIdentification.getDrugShape())
                .dosageInstructions(drugSummary.getUseMethodQesitm())
                .size(drugIdentification.getLengLong())
                .slotNumber(String.valueOf(updatedSlot.getSlotNumber()))
                .slotSize(request.getDiskSize())
                .build();
    }
}
