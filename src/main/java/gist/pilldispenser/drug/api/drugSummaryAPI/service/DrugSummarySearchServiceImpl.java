package gist.pilldispenser.drug.api.drugSummaryAPI.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.QDrugProduct;
import gist.pilldispenser.drug.api.drugProductAPI.repository.DrugProductRepository;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response.DrugSummaryDTO;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.QDrugSummary;
import gist.pilldispenser.drug.api.drugSummaryAPI.repository.DrugSummaryRepository;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.UserDrugRoutineResponse;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.QUserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.repository.UserDrugInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.jpa.JPAExpressions.select;

@Service
@RequiredArgsConstructor
public class DrugSummarySearchServiceImpl implements DrugSummarySearchService {

    private final DrugSummaryRepository drugSummaryRepository;
    private final DrugProductRepository drugProductRepository;
    private final UserDrugInfoRepository userDrugInfoRepository;
    private final JPAQueryFactory queryFactory;


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
                    .map(drug -> new DrugSummaryDTO(drug.getEntpName(), drug.getItemName(), drug.getItemSeq(), drug.getItemImage()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("약 이름으로 약물을 검색하는 중 오류가 발생했습니다: " + itemName, e);
        }
    }


    // itemSeq를 기반으로 혼용이 안 되는 성분을 찾아서 해당 성분을 포함하는 약물 리스트 반환
    @Transactional(readOnly = true)
    public List<String> findDrugsByConflictingComponents(String itemSeq, Long userId) throws Exception {
        try {
            QDrugSummary drugSummary = QDrugSummary.drugSummary;
            QDrugProduct drugProduct = QDrugProduct.drugProduct;
            List<UserDrugInfo> userDrugInfos = userDrugInfoRepository.findByUserId(userId);

            String conflictingComponents = queryFactory
                    .select(drugSummary.intrcQesitm)
                    .from(drugSummary)
                    .where(drugSummary.itemSeq.eq(itemSeq))
                    .fetchOne();

            if (conflictingComponents == null || conflictingComponents.isEmpty()) {
                return new ArrayList<>();
            }

            String[] componentsArray = conflictingComponents.split(",");
            List<String> components = Arrays.asList(componentsArray);

            List<String> confilctList = queryFactory
                    .select(drugProduct.itemSeq)
                    .from(drugProduct)
                    .where(drugProduct.mtralNm.in(components))
                    .fetch();

            List<String> result = new ArrayList<>();
            for (String conflict : confilctList) {
                for (UserDrugInfo userDrugInfo : userDrugInfos) {
                    if (userDrugInfo.getFullMedicationInfo() != null && userDrugInfo.getFullMedicationInfo().getItemSeq().equals(conflict)) {
                        result.add(userDrugInfo.getFullMedicationInfo().getDrugSummary().getItemName());
                    }
                }
            }

            return result;
        } catch (Exception e) {
            throw new Exception("itemSeq로 혼용 금지 성분을 조회하는 중 오류가 발생했습니다: " + itemSeq, e);
        }
    }

    // 특정 성분으로 혼용 금지 성분을 포함하는 약물 리스트 반환
    @Transactional(readOnly = true)
    public List<String> findDrugsByComponentName(String componentName, Long userId) throws Exception {
        List<String> conflictingDrugs = new ArrayList<>();
        try {
            List<UserDrugInfo> userDrugInfos = userDrugInfoRepository.findByUserId(userId);

            for (UserDrugInfo userDrugInfo : userDrugInfos) {
                if (userDrugInfo.getFullMedicationInfo() != null) {
                    String itemSeq = userDrugInfo.getFullMedicationInfo().getItemSeq();
                    DrugProduct drugProduct = drugProductRepository.findByItemSeq(itemSeq);

                    if (drugProduct.getMtralNm().contains(componentName)) {
                        conflictingDrugs.add(drugProduct.getPrduct());
                    }
                }
            }

            return conflictingDrugs;
        } catch (Exception e) {
            throw new Exception("특정 성분으로 혼용 금지 성분을 포함하는 약물을 조회하는 중 오류가 발생했습니다: " + componentName, e);
        }
    }

}