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

    @Schema(description = "슬롯 id", example = "1")
    private Long slotId;

    @Schema(description = "슬롯 번호", example = "1")
    private int slotNumber;
}
