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
public class FreeSlotRequestDto {

    @Schema(description = "비울 슬롯 번호", example = "2")
    private int slotNumber;

}
