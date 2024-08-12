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
public class EmptySlotResponseDto {

    @Schema(description = "빈 슬롯 번호", example = "2")
    private int slotNumber;

    @Schema(description = "슬롯의 크기 (S, M, L)", example = "M")
    private String size;


}
