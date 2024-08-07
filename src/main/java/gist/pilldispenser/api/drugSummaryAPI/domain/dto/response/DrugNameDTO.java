package gist.pilldispenser.api.drugSummaryAPI.domain.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrugNameDTO {

    @Schema(description = "알약명", example = "타이레놀")
    private String itemName;

}