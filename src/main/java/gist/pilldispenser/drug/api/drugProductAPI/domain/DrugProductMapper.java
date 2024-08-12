package gist.pilldispenser.drug.api.drugProductAPI.domain;

import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;
import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.DrugProductRequest;

public class DrugProductMapper {

    public static DrugProduct toDrugProduct(DrugProductRequest request) {
        return DrugProduct.builder()
                .entrps(request.getEntrps())
                .prduct(request.getPrduct())
                .mtralNm(request.getMtralNm())
                .itemSeq(request.getItemSeq())
                .build();
    }
}
