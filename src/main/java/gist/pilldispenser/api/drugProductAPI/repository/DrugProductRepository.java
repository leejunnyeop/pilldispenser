package gist.pilldispenser.api.drugProductAPI.repository;

import gist.pilldispenser.api.drugProductAPI.domain.dto.DrugProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugProductRepository extends JpaRepository<DrugProduct, Long> {
}
