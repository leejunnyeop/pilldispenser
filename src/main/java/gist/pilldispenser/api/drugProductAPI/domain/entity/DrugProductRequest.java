package gist.pilldispenser.api.drugProductAPI.domain.entity;

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
