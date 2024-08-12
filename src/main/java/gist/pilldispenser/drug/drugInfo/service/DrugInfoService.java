package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequestBase;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoResponse;

public interface DrugInfoService {

  DrugInfoResponse createDrugInfoManually(Long userId, DrugInfoRequestBase drugInfoRequest);


}
