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
import gist.pilldispenser.drug.medication.domain.MedicationDetail;
import gist.pilldispenser.drug.medication.repository.MedicationDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationDetailService {

    private final MedicationDetailRepository medicationDetailRepository;
    private final DrugSummaryRepository drugSummaryRepository;
    private final DrugIdentificationRepository drugIdentificationRepository;
    private final DrugProductRepository drugProductRepository;
    private final JPAQueryFactory queryFactory;

    @Transactional
    public MedicationDetail saveMedicationDetailByItemSeq(String itemSeq) {

        QDrugSummary drugSummary = QDrugSummary.drugSummary;
        QDrugIdentification drugIdentification = QDrugIdentification.drugIdentification;
        QDrugProduct drugProduct = QDrugProduct.drugProduct;

        DrugSummary fetchedDrugSummary = queryFactory.selectFrom(drugSummary)
                .where(drugSummary.itemSeq.eq(itemSeq))
                .fetchOne();

        if (fetchedDrugSummary == null) {
            throw new IllegalArgumentException("해당 itemSeq에 대한 DrugSummary 정보를 찾을 수 없습니다: " + itemSeq);
        }

        DrugIdentification fetchedDrugIdentification = queryFactory.selectFrom(drugIdentification)
                .where(drugIdentification.itemSeq.eq(itemSeq))
                .fetchOne();

        if (fetchedDrugIdentification == null) {
            throw new IllegalArgumentException("해당 itemSeq에 대한 DrugIdentification 정보를 찾을 수 없습니다: " + itemSeq);
        }

        List<DrugProduct> fetchedDrugProducts = queryFactory.selectFrom(drugProduct)
                .where(drugProduct.itemSeq.eq(itemSeq))
                .fetch();

        if (fetchedDrugProducts.isEmpty()) {
            throw new IllegalArgumentException("해당 itemSeq에 대한 DrugProduct 정보를 찾을 수 없습니다: " + itemSeq);
        }

        // MedicationDetail 엔티티 생성 및 저장
        MedicationDetail medicationDetail = MedicationDetail.builder()
                .itemSeq(itemSeq)
                .drugSummary(fetchedDrugSummary)
                .drugIdentification(fetchedDrugIdentification)
                .drugProducts(fetchedDrugProducts)
                .build();

        return medicationDetailRepository.save(medicationDetail);
    }


    @Transactional(readOnly = true)
    public MedicationDetail findMedicationDetailByItemSeq(String itemSeq) {
        return medicationDetailRepository.findByItemSeq(itemSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 itemSeq에 대한 MedicationDetail 정보를 찾을 수 없습니다: " + itemSeq));
    }
}
