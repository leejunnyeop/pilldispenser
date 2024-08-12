package gist.pilldispenser.drug.drugInfo.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.QDrugIdentification;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.QDrugProduct;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.QDrugSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DrugInfoRepositoryCustomImpl implements DrugInfoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Tuple findDrugIdentificationAndSummaryAndProductByItemSeq(String itemSeq) {

        QDrugIdentification qDrugIdentification = QDrugIdentification.drugIdentification;
        QDrugSummary qDrugSummary = QDrugSummary.drugSummary;
        QDrugProduct qDrugProduct = QDrugProduct.drugProduct;

        return queryFactory.select(qDrugIdentification, qDrugSummary, qDrugProduct)
                .from(qDrugIdentification)
                .leftJoin(qDrugSummary).on(qDrugIdentification.itemSeq.eq(qDrugSummary.itemSeq))
                .leftJoin(qDrugProduct).on(qDrugSummary.itemSeq.eq(qDrugProduct.itemSeq))
                .where(qDrugIdentification.itemSeq.eq(itemSeq))
                .fetchOne();
    }
}
