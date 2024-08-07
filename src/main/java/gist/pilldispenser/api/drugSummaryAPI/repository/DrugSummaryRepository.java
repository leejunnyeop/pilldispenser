package gist.pilldispenser.api.drugSummaryAPI.repository;

import gist.pilldispenser.api.drugSummaryAPI.custom.DrugSummaryRepositoryCustom;
import gist.pilldispenser.api.drugSummaryAPI.domain.entity.DrugSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugSummaryRepository extends JpaRepository<DrugSummary, Long>, DrugSummaryRepositoryCustom {
}
