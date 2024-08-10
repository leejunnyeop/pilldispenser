package gist.pilldispenser.drug.userDrugInfo.repository;

import com.querydsl.core.Tuple;

import java.util.List;

public interface UserDrugInfoRepositoryCustom {

    Tuple findUserDrugInfoAndDrugInfoAndUserByRoutineId(Long routineId);
}
