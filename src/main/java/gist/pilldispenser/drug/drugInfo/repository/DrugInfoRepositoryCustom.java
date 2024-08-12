package gist.pilldispenser.drug.drugInfo.repository;

import com.querydsl.core.Tuple;

public interface DrugInfoRepositoryCustom {

    Tuple findDrugIdentificationAndSummaryAndProductByItemSeq(String itemSeq);

}
