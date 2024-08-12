package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "특정 약물 정보 조회 응답 DTO")
public class DrugDetailResponse {

    @Schema(description = "약물 이름", example = "아스피린")
    private String drugName;

    @Schema(description = "제약회사 이름", example = "제약회사 이름")
    private String entpName;

    @Schema(description = "제약회사 이미지 URL", example = "http://example.com/image.png", nullable = true)
    private String entpImage;

    @Builder
    public DrugDetailResponse(String drugName, String entpName, String entpImage) {
        this.drugName = drugName;
        this.entpName = entpName;
        this.entpImage = entpImage;
    }
}
