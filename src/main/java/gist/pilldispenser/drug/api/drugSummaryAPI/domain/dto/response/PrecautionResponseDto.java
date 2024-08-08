package gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrecautionResponseDto {
    private String atpnQesitm; // 먹지 말아야하는 대상
    private String intrcQesitm; // 혼용 금지 성분
    private String seQesitm; // 주의사항
}