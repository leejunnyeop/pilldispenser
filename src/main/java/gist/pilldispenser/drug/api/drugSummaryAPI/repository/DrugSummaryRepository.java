package gist.pilldispenser.drug.api.drugSummaryAPI.repository;

import gist.pilldispenser.drug.api.drugSummaryAPI.custom.DrugSummaryRepositoryCustom;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugSummaryRepository extends JpaRepository<DrugSummary, Long>, DrugSummaryRepositoryCustom {

   Optional<DrugSummary> findByItemSeq(String itemSeq);

}
