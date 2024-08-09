package gist.pilldispenser.drug.userDrugInfo.repository;

import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}
