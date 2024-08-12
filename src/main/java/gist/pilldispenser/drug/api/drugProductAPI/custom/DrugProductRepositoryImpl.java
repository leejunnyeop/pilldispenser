package gist.pilldispenser.drug.api.drugProductAPI.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.QDrugProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DrugProductRepositoryImpl implements DrugProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> findDistinctMtralNmByPartialName(String partialName) {
        QDrugProduct drugProduct = QDrugProduct.drugProduct;
        return queryFactory
                .select(drugProduct.mtralNm)
                .distinct()
                .from(drugProduct)
                .where(drugProduct.mtralNm.containsIgnoreCase(partialName))
                .fetch();
    }
}

