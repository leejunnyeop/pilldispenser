package gist.pilldispenser.drug.api.drugSummaryAPI.domain;

import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.request.DrugSummaryRequest;

public class DrugMapper {

    public static DrugSummary toEntity(DrugSummaryRequest drugSummaryRequest) {
        return DrugSummary.builder()
                .entpName(drugSummaryRequest.getEntpName())
                .itemName(drugSummaryRequest.getItemName())
                .itemSeq(drugSummaryRequest.getItemSeq())
                .useMethodQesitm(drugSummaryRequest.getUseMethodQesitm())
                .intrcQesitm(drugSummaryRequest.getIntrcQesitm())
                .itemImage(drugSummaryRequest.getItemImage())

                .build();
    }
}
