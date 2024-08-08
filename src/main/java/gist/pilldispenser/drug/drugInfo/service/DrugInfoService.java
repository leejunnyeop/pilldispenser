package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;

public interface DrugInfoService {

    void createDrugInfo(Long userId, DrugInfoRequest DrugInfoRequest);


}
