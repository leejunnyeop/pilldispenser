package gist.pilldispenser.api.drugSummaryAPI.custom;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.api.drugSummaryAPI.domain.entity.DrugSummary;

import gist.pilldispenser.api.drugSummaryAPI.domain.entity.QDrugSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrugSummaryRepositoryCustomImpl implements DrugSummaryRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public List<DrugSummary> searchByItemName(String itemName) {
        QDrugSummary drugSummary = QDrugSummary.drugSummary;
        return queryFactory.selectFrom(drugSummary)
                .where(itemNameContains(itemName))
                .fetch();
    }

    @Override
    public List<String> searchItemNames(String itemName) {
        QDrugSummary drugSummary = QDrugSummary.drugSummary;
        return queryFactory.select(drugSummary.itemName)
                .from(drugSummary)
                .where(itemNameContains(itemName))
                .fetch();
    }

    private BooleanExpression itemNameContains(String itemName) {
        QDrugSummary drugSummary = QDrugSummary.drugSummary;
        return itemName != null ? drugSummary.itemName.containsIgnoreCase(itemName) : null;
    }
}
