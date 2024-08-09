package gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DrugSummaryRequest {

    private String entpName; // 회사명
    private String itemName; // 알약명
    private String itemSeq; // 알약 고유번호
    private String efcyQesitm;  // 성능
    private String useMethodQesitm; // 용법
    private String atpnQesitm; // 먹지 말아야하는 대상
    private String intrcQesitm; // 혼용 금지 성분?
    private String seQesitm; //주의사항
    private String depositMethodQesitm; // 보관방법
    private String itemImage; // 이미지 이거 사용

    @Builder
    public DrugSummaryRequest(String entpName, String itemName, String itemSeq, String efcyQesitm, String useMethodQesitm, String atpnQesitm, String intrcQesitm, String seQesitm, String depositMethodQesitm,  String itemImage) {
        this.entpName = entpName;
        this.itemName = itemName;
        this.itemSeq = itemSeq;
        this.efcyQesitm = efcyQesitm;
        this.useMethodQesitm = useMethodQesitm;
        this.atpnQesitm = atpnQesitm;
        this.intrcQesitm = intrcQesitm;
        this.seQesitm = seQesitm;
        this.depositMethodQesitm = depositMethodQesitm;
        this.itemImage = itemImage;

    }
}