package gist.pilldispenser.drug.medication.repository;

import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;

public interface FullMedicationInfoRepository extends JpaRepository<FullMedicationInfo, Long> {

    @EntityGraph(value = "FullMedicationInfo.withRelations", type = EntityGraph.EntityGraphType.LOAD)
    Optional<FullMedicationInfo> findByItemSeq(String itemSeq);

}
