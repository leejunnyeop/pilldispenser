package gist.pilldispenser.api.drugProductAPI.domain.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugProductApiResponse {

    @JsonProperty("header")
    private Header header;

    @JsonProperty("body")
    private Body body;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Header {
        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("totalCount")
        private int totalCount;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("items")
        private List<DrugItem> items;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DrugItem {


        @JsonProperty("ENTRPS")
        private String entrps;

        @JsonProperty("PRDUCT")
        private String prduct;

        @JsonProperty("MTRAL_SN")
        private String mtralSn;

        @JsonProperty("MTRAL_CODE")
        private String mtralCode;

        @JsonProperty("MTRAL_NM")
        private String mtralNm;

        @JsonProperty("QNT")
        private String qnt;

        @JsonProperty("INGD_UNIT_CD")
        private String ingdUnitCd;

        @JsonProperty("ITEM_SEQ")
        private String itemSeq;


    }
}
