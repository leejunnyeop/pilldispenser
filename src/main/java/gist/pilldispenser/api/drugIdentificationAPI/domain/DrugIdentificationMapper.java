package gist.pilldispenser.api.drugIdentificationAPI.domain;

import gist.pilldispenser.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.api.drugIdentificationAPI.domain.entity.DrugIdentificationRequest;

public class DrugIdentificationMapper {

    public static DrugIdentification toDrugIdentification(DrugIdentificationRequest request) {
        return DrugIdentification.builder()
                .itemSeq(request.getItemSeq())
                .itemName(request.getItemName())
                .entpName(request.getEntpName())
                .chart(request.getChart())
                .drugShape(request.getDrugShape())
                .colorClass1(request.getColorClass1())
                .colorClass2(request.getColorClass2())
                .lengLong(request.getLengLong())
                .lengShort(request.getLengShort())
                .thick(request.getThick())
                .build();

    }
}
