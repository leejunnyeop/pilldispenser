package gist.pilldispenser.drug.api.drugSummaryAPI.repository;

import gist.pilldispenser.drug.api.drugSummaryAPI.custom.DrugSummaryRepositoryCustom;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugSummaryRepository extends JpaRepository<DrugSummary, Long>, DrugSummaryRepositoryCustom {
}
