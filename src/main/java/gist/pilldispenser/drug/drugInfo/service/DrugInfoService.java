package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;

import java.util.List;
import java.util.Optional;

public interface DrugInfoService {

    void createDrugInfo(DrugInfoRequest DrugInfoRequest);


}
