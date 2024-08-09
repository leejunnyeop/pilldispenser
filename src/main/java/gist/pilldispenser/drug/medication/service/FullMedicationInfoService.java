package gist.pilldispenser.drug.medication.service;


import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.QDrugIdentification;
import gist.pilldispenser.drug.api.drugIdentificationAPI.repository.DrugIdentificationRepository;
import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.DrugProduct;
import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.QDrugProduct;
import gist.pilldispenser.drug.api.drugProductAPI.repository.DrugProductRepository;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.QDrugSummary;
import gist.pilldispenser.drug.api.drugSummaryAPI.repository.DrugSummaryRepository;
import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import gist.pilldispenser.drug.medication.domain.dto.FullMedicationInfoMapper;
import gist.pilldispenser.drug.medication.domain.dto.FullMedicationInfoRequestDto;
import gist.pilldispenser.drug.medication.repository.FullMedicationInfoRepository;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FullMedicationInfoService {

    private final FullMedicationInfoRepository fullMedicationInfoRepository;
    private final DrugSummaryRepository drugSummaryRepository;
    private final DrugIdentificationRepository drugIdentificationRepository;
    private final DrugProductRepository drugProductRepository;
    private final JPAQueryFactory queryFactory;
    private final UsersRepository usersRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;

    @Transactional
    public void saveFullMedicationInfoByItemSeq(Long userId, String itemSeq) {

        FullMedicationInfoRequestDto dto = FullMedicationInfoMapper.toFullMedicationInfoRequestDto(userId, itemSeq);
        QDrugSummary drugSummary = QDrugSummary.drugSummary;
        QDrugIdentification drugIdentification = QDrugIdentification.drugIdentification;
        QDrugProduct drugProduct = QDrugProduct.drugProduct;

        DrugSummary fetchedDrugSummary = queryFactory.selectFrom(drugSummary)
                .where(drugSummary.itemSeq.eq(dto.getItemSeq()))
                .fetchOne();

        if (fetchedDrugSummary == null) {
            throw new IllegalArgumentException("해당 itemSeq에 대한 DrugSummary 정보를 찾을 수 없습니다: " + dto.getItemSeq());
        }

        DrugIdentification fetchedDrugIdentification = queryFactory.selectFrom(drugIdentification)
                .where(drugIdentification.itemSeq.eq(dto.getItemSeq()))
                .fetchOne();

        if (fetchedDrugIdentification == null) {
            throw new IllegalArgumentException("해당 itemSeq에 대한 DrugIdentification 정보를 찾을 수 없습니다: " + dto.getItemSeq());
        }

        List<DrugProduct> fetchedDrugProducts = queryFactory.selectFrom(drugProduct)
                .where(drugProduct.itemSeq.eq(dto.getItemSeq()))
                .fetch();

        if (fetchedDrugProducts.isEmpty()) {
            throw new IllegalArgumentException("해당 itemSeq에 대한 DrugProduct 정보를 찾을 수 없습니다: " + dto.getItemSeq());
        }

        FullMedicationInfo fullMedicationInfo = FullMedicationInfoMapper.toFullMedicationInfo(dto, fetchedDrugSummary, fetchedDrugIdentification, fetchedDrugProducts);
        fullMedicationInfoRepository.save(fullMedicationInfo);

        Users user = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 userId에 대한 사용자 정보를 찾을 수 없습니다: " + dto.getUserId()));

        UserDrugInfo userDrugInfo = UserDrugInfo.create(user, null, fullMedicationInfo);
        userDrugInfoRepository.save(userDrugInfo);
    }

}