package gist.pilldispenser.api.drugSummaryAPI.domain;

import gist.pilldispenser.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.api.drugSummaryAPI.domain.entity.DrugSummaryRequest;

public class DrugMapper {

    public static DrugSummary toEntity(DrugSummaryRequest drugSummaryRequest) {
        return DrugSummary.builder()
                .entpName(drugSummaryRequest.getEntpName())
                .itemName(drugSummaryRequest.getItemName())
                .itemSeq(drugSummaryRequest.getItemSeq())
                .efcyQesitm(drugSummaryRequest.getEfcyQesitm())
                .useMethodQesitm(drugSummaryRequest.getUseMethodQesitm())
                .atpnQesitm(drugSummaryRequest.getAtpnQesitm())
                .intrcQesitm(drugSummaryRequest.getIntrcQesitm())
                .seQesitm(drugSummaryRequest.getSeQesitm())
                .depositMethodQesitm(drugSummaryRequest.getDepositMethodQesitm())

                .itemImage(drugSummaryRequest.getItemImage())

                .build();
    }
}
