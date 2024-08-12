package gist.pilldispenser.drug.userDrugInfo.repository;

import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findByUserDrugInfoUserId(Long userId);

    List<Routine> findByUserDrugInfoId(Long userId);



}
