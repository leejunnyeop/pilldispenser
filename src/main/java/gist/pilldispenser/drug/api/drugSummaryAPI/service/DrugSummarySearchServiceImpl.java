package gist.pilldispenser.drug.api.drugSummaryAPI.service;

import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.DrugNameDTO;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.DrugSummaryDTO;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.PrecautionResponseDto;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.api.drugSummaryAPI.repository.DrugSummaryRepository;
import gist.pilldispenser.drug.userDrugInfo.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.UserDrugInfoRepository;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugSummarySearchServiceImpl implements DrugSummarySearchService {


    private final UsersRepository usersRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;
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

    public List<DrugNameDTO> searchItemNames(String itemName) throws Exception {
        try {
            List<String> itemNames = drugSummaryRepository.searchItemNames(itemName);
            return itemNames.stream()
                    .map(DrugNameDTO::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("약 이름을 검색하는 중 오류가 발생했습니다: " + itemName, e);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public PrecautionResponseDto findContraindicationsForDrug(Long userId, String itemSeq) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 userId에 대한 사용자 정보를 찾을 수 없습니다: " + userId));

        List<UserDrugInfo> userDrugInfos = userDrugInfoRepository.findByUserId(userId);

        boolean hasDrug = userDrugInfos.stream()
                .anyMatch(userDrugInfo -> userDrugInfo.getFullMedicationInfo().getItemSeq().equals(itemSeq));

        if (!hasDrug) {
            throw new IllegalArgumentException("사용자가 복용 중인 약 정보가 없습니다: " + itemSeq);
        }

        DrugSummary drugSummary = drugSummaryRepository.findByItemSeq(itemSeq)
                .orElseThrow(() -> new IllegalArgumentException("해당 itemSeq에 대한 DrugSummary 정보를 찾을 수 없습니다: " + itemSeq));

        return new PrecautionResponseDto(
                drugSummary.getAtpnQesitm(),
                drugSummary.getIntrcQesitm(),
                drugSummary.getSeQesitm()
        );
    }


}
