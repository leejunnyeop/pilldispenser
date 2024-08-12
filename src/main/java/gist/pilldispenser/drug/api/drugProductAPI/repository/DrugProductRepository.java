package gist.pilldispenser.drug.api.drugProductAPI.repository;

import gist.pilldispenser.drug.api.drugProductAPI.custom.DrugProductRepositoryCustom;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugProductRepository extends JpaRepository<DrugProduct, Long> , DrugProductRepositoryCustom {
}
