package gist.pilldispenser.drug.drugInfo.domain;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugManualInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugRegistrationResponse;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugShape;

public class DrugInfoMapper {
    public static DrugInfo toDrugInfo(DrugManualInfoRequest request) {
        return DrugInfo.builder()
                .name(request.getName())
                .dosage(request.getDosage())
                .mtralNm(request.getMtralNm())
                .dailyDosage(request.getDailyDosage())
                .shape(DrugShape.fromDescription(request.getShape()))
                .longAxis(request.getLongAxis())
                .shortAxis(request.getShortAxis())
                .build();
    }

}
