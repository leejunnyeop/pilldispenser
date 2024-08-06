package gist.pilldispenser.api.drugIdentificationAPI.repository;

import gist.pilldispenser.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugIdentificationRepository extends JpaRepository<DrugIdentification, Long> {
}
