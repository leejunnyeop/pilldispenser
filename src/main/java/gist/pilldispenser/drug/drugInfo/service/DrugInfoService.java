package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugAutoRegistrationRequest;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequestBase;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugRegistrationResponse;

public interface DrugInfoService {

  DrugRegistrationResponse createDrugInfoManually(Long userId, DrugInfoRequestBase drugInfoRequest);

  DrugRegistrationResponse createDrugInfoAutomatically(UsersDetails usersDetails, DrugAutoRegistrationRequest request);

}
