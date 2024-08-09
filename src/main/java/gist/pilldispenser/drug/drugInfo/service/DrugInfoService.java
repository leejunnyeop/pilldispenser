package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;

public interface DrugInfoService {

   void createDrugInfoManually(Long userId, DrugInfoRequest drugInfoRequest);


}
