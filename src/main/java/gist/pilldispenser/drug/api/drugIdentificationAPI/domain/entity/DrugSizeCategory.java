package gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrugSizeCategory {

    @Schema(description = "약물의 형태 (예: 원형, 타원형 등)", example = "원형")
    private String shape;  // 약물의 형태

    @Schema(description = "약물의 크기 (예: S, M, L)", example = "M")
    private String size;  // 약물의 크기
}
