package gist.pilldispenser.drug.api.drugIdentificationAPI.repository;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugIdentificationRepository extends JpaRepository<DrugIdentification, Long>  {

    Optional<DrugIdentification> findByItemSeq(String itemSeq);

}
