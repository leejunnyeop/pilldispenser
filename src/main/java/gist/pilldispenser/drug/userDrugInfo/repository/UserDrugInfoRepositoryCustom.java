package gist.pilldispenser.drug.userDrugInfo.repository;

import com.querydsl.core.Tuple;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;

public interface UserDrugInfoRepositoryCustom {

    Tuple findUserDrugInfoAndDrugInfoByRoutineId(Long routineId);

    Tuple findUserDrugInfoAndFullMedicationInfoByRoutineId(Long routineId);

    UserDrugInfo getUserDrugInfoByRoutine(Long routineId);

    DrugIdentification getDrugIdentificationByFullMedicationInfo(FullMedicationInfo fullMedicationInfo);
}
