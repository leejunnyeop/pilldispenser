package gist.pilldispenser.drug.api.drugIdentificationAPI.repository;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugIdentificationRepository extends JpaRepository<DrugIdentification, Long> {
}
