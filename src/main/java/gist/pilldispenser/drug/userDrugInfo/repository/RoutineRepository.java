package gist.pilldispenser.drug.userDrugInfo.repository;

import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findByUserDrugInfoUserId(Long userId);
}
