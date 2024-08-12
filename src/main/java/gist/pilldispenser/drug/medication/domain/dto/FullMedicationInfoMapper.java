package gist.pilldispenser.drug.medication.domain.dto;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;


import java.util.List;

public class FullMedicationInfoMapper {

    public static FullMedicationInfoRequestDto toFullMedicationInfoRequestDto(Long userId, String itemSeq) {
        return FullMedicationInfoRequestDto.builder()
                .userId(userId)
                .itemSeq(itemSeq)
                .build();
    }

    public static FullMedicationInfo toFullMedicationInfo(FullMedicationInfoRequestDto dto, DrugSummary drugSummary,
                                                          DrugIdentification drugIdentification, List<DrugProduct> drugProducts) {
        return FullMedicationInfo.builder()
                .itemSeq(dto.getItemSeq())
                .drugSummary(drugSummary)
                .drugIdentification(drugIdentification)
                .drugProducts(drugProducts)
                .build();
    }

}
