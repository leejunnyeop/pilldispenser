package gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PrecautionResponseDto {
    @Schema(description = "복용하지 말아야 하는 대상", example = "이 약에 과민증이 있는 환자")
    private String atpnQesitm; // 먹지 말아야하는 대상

    @Schema(description = "혼용 금지 성분", example = "레보도파와 함께 복용하지 마십시오")
    private String intrcQesitm; // 혼용 금지 성분

    @Schema(description = "주의사항", example = "구역, 구토, 설사 등의 부작용이 나타날 수 있습니다")
    private String seQesitm; // 주의사항
}