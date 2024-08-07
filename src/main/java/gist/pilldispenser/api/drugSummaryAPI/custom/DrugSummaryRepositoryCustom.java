package gist.pilldispenser.api.drugSummaryAPI.custom;

import gist.pilldispenser.api.drugSummaryAPI.domain.entity.DrugSummary;

import java.util.List;

public interface DrugSummaryRepositoryCustom {
    List<DrugSummary> searchByItemName(String itemName);

    List<String> searchItemNames(String itemName);
}
