package gist.pilldispenser.drug.medication.repository;

import gist.pilldispenser.drug.medication.domain.FullMedicationInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MedicationDetailRepository extends JpaRepository<FullMedicationInfo, Long> {

    @EntityGraph(value = "FullMedicationInfo.withRelations", type = EntityGraph.EntityGraphType.LOAD)
    Optional<FullMedicationInfo> findByItemSeq(String itemSeq);

    @Query("SELECT um.itemSeq FROM UserMedication um WHERE um.userId = :userId")
    List<String> findItemSeqsByUserId(Long userId);
}
