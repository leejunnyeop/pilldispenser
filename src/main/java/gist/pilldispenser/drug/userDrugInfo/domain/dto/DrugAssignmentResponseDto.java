package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugAssignmentResponseDto {

    @Schema(description = "슬롯 번호", example = "1")
    private int slotNumber;

    @Schema(description = "할당된 약물 ID", example = "123456")
    private Long drugId;

    @Schema(description = "할당된 슬롯의 크기", example = "M")
    private String size;

}
