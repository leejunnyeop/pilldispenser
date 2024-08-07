package gist.pilldispenser.api.drugSummaryAPI.service;

import gist.pilldispenser.api.drugSummaryAPI.domain.dto.response.DrugSummaryDTO;

import java.util.List;

public interface DrugSummarySearchService  {

    public List<DrugSummaryDTO> searchDrugsByName(String itemName) throws Exception;
}
