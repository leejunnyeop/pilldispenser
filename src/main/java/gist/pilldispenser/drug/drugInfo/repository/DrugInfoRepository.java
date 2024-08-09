package gist.pilldispenser.drug.drugInfo.repository;

import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugInfoRepository extends JpaRepository<DrugInfo, Long> {
}
