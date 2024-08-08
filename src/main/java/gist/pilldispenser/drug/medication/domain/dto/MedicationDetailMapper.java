package gist.pilldispenser.drug.medication.domain.dto;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.DrugProduct;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.medication.domain.FullMedicationInfo;
import gist.pilldispenser.users.domain.entity.Users;

import java.util.List;

public class MedicationDetailMapper {

    public static FullMedicationInfo toMedicationDetail(MedicationDetailRequestDto dto, DrugSummary drugSummary, DrugIdentification drugIdentification, List<DrugProduct> drugProducts, Users user) {
        return FullMedicationInfo.builder()
                .itemSeq(dto.getItemSeq())
                .drugSummary(drugSummary)
                .drugIdentification(drugIdentification)
                .drugProducts(drugProducts)
                .build();
    }

    public static MedicationDetailRequestDto toMedicationDetailRequestDto(Long userId, String itemSeq) {
        return MedicationDetailRequestDto.builder()
                .userId(userId)
                .itemSeq(itemSeq)
                .build();
    }

}
