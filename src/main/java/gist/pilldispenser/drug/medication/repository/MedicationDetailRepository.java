package gist.pilldispenser.drug.medication.repository;

import gist.pilldispenser.drug.medication.domain.MedicationDetail;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationDetailRepository extends JpaRepository<MedicationDetail, Long> {

    @EntityGraph(value = "MedicationDetail.withRelations", type = EntityGraph.EntityGraphType.LOAD)
    Optional<MedicationDetail> findByItemSeq(String itemSeq);
}
