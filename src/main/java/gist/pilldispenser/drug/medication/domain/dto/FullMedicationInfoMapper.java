package gist.pilldispenser.drug.medication.domain.dto;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.DrugProduct;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.medication.domain.FullMedicationInfo;
import gist.pilldispenser.users.domain.entity.Users;

import java.util.List;

public class FullMedicationInfoMapper {

    public static FullMedicationInfo toFullMedicationInfo(FullMedicationInfoRequestDto dto, DrugSummary drugSummary, DrugIdentification drugIdentification, List<DrugProduct> drugProducts, Users user) {
        return FullMedicationInfo.builder()
                .itemSeq(dto.getItemSeq())
                .drugSummary(drugSummary)
                .drugIdentification(drugIdentification)
                .drugProducts(drugProducts)
                .build();
    }

    public static FullMedicationInfoRequestDto toFullMedicationInfoRequestDto(Long userId, String itemSeq) {
        return FullMedicationInfoRequestDto.builder()
                .userId(userId)
                .itemSeq(itemSeq)
                .build();
    }

}
