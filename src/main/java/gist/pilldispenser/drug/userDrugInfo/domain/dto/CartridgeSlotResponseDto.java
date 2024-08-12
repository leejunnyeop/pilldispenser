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
public class CartridgeSlotResponseDto {

    @Schema(description = "슬롯 번호", example = "1")
    private int slotNumber;

    @Schema(description = "슬롯의 크기 (S, M, L)", example = "M")
    private String size;

    @Schema(description = "해당 슬롯이 사용 중인지 여부", example = "false")
    private boolean isOccupied;

}
