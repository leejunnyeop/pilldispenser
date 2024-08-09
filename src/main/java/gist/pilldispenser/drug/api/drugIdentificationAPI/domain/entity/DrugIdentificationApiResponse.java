package gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity;


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
public class DrugIdentificationApiResponse {

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
        @JsonProperty("ITEM_SEQ")
        private String itemSeq;

        @JsonProperty("ITEM_NAME")
        private String itemName;

        @JsonProperty("ENTP_NAME")
        private String entpName;

        @JsonProperty("CHART")
        private String chart;

        @JsonProperty("DRUG_SHAPE")
        private String drugShape;

        @JsonProperty("COLOR_CLASS1")
        private String colorClass1;

        @JsonProperty("COLOR_CLASS2")
        private String colorClass2;

        @JsonProperty("LENG_LONG")
        private String lengLong;

        @JsonProperty("LENG_SHORT")
        private String lengShort;

        @JsonProperty("THICK")
        private String thick;

    }
}

