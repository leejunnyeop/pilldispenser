package gist.pilldispenser.api.drugSummaryAPI.service;

import gist.pilldispenser.api.drugSummaryAPI.domain.dto.DrugSummaryDTO;
import gist.pilldispenser.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.api.drugSummaryAPI.repository.DrugSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugSummarySearchServiceImpl implements DrugSummarySearchService {

    private final DrugSummaryRepository drugSummaryRepository;

    /**
     * 주어진 약 이름으로 약물 정보를 검색합니다.
     *
     * @param itemName 검색할 약 이름
     * @return 검색된 약물 정보 리스트
     */

    @Override
    public List<DrugSummaryDTO> searchDrugsByName(String itemName) throws Exception {
        try {
            List<DrugSummary> drugSummaries = drugSummaryRepository.searchByItemName(itemName);
            return drugSummaries.stream()
                    .map(drug -> new DrugSummaryDTO(drug.getItemName(), drug.getItemSeq(), drug.getItemImage()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("약 이름으로 약물을 검색하는 중 오류가 발생했습니다: " + itemName, e);
        }

    }
}
