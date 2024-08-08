package gist.pilldispenser.drug.api.drugSummaryAPI.custom;

import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;

import java.util.List;

public interface DrugSummaryRepositoryCustom {
    List<DrugSummary> searchByItemName(String itemName);

    List<String> searchItemNames(String itemName);
}
