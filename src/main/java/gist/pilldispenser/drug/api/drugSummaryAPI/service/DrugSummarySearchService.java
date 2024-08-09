package gist.pilldispenser.drug.api.drugSummaryAPI.service;

import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.DrugSummaryDTO;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.PrecautionResponseDto;

import java.util.List;

public interface DrugSummarySearchService  {

    public List<DrugSummaryDTO> searchDrugsByName(String itemName) throws Exception;

    public PrecautionResponseDto findContraindicationsForDrug(Long userId, String itemSeq);
}
