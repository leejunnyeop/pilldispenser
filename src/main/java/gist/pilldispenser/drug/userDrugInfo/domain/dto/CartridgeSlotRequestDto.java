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
public class CartridgeSlotRequestDto {

    @Schema(description = "사용자 약물 정보 ID", example = "111111")
    private Long userDrugInfoId;

    @Schema(description = "할당할 슬롯 번호", example = "3")
    private int slotNumber;

    @Schema(description = "슬롯의 크기 (S, M, L)", example = "M")
    private String size;

}