package gist.pilldispenser.drug.api.drugSummaryAPI.domain.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrugSummaryDTO {


    @Schema(description = "회사명", example = "유한켐버린")
    private String entpName; // 회사명

    @Schema(description = "알약명", example = "타이레놀")
    private String itemName;

    @Schema(description = "알약 고유번호", example = "200808876")
    private String itemSeq;

    @Schema(description = "이미지 URL", example = "https://example.com/image.jpg")
    private String itemImage;
}