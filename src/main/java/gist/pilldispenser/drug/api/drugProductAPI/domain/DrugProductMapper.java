package gist.pilldispenser.drug.api.drugProductAPI.domain;

import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.DrugProduct;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProductRequest;

public class DrugProductMapper {

    public static DrugProduct toDrugProduct(DrugProductRequest request) {
        return DrugProduct.builder()
                .entrps(request.getEntrps())
                .prduct(request.getPrduct())
                .mtralSn(request.getMtralSn())
                .mtralCode(request.getMtralCode())
                .mtralNm(request.getMtralNm())
                .qnt(request.getQnt())
                .ingdUnitCd(request.getIngdUnitCd())
                .itemSeq(request.getItemSeq())
                .build();
    }
}
