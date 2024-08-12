package gist.pilldispenser.drug.api.drugProductAPI.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DrugProductRequest {

    private String entrps;
    private String prduct;
    private String mtralSn;
    private String mtralCode;
    private String mtralNm;
    private String qnt;
    private String ingdUnitCd;
    private String itemSeq;

}
