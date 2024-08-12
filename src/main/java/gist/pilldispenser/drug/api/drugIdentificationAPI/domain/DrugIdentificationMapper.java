package gist.pilldispenser.drug.api.drugIdentificationAPI.domain;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentificationRequest;

public class DrugIdentificationMapper {

    public static DrugIdentification toDrugIdentification(DrugIdentificationRequest request) {
        return DrugIdentification.builder()
                .itemSeq(request.getItemSeq())
                .itemName(request.getItemName())
                .entpName(request.getEntpName())
                .drugShape(request.getDrugShape())
                .lengLong(request.getLengLong())
                .lengShort(request.getLengShort())
                .build();

    }
}
