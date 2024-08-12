package gist.pilldispenser.drug.userDrugInfo.domain.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "모든 루틴 정보 조회 응답 DTO")
public class AllRoutineResponse {

    @Schema(description = "DrugInfo ID", example = "12345")
    private Long drugInfoId;

    @Schema(description = "약물 이름", example = "아스피린")
    private String drugName;

    @Schema(description = "약물 복용량", example = "500mg")
    private String dosage;

    @Schema(description = "FullMedicationInfo의 itemSeq", example = "123456789")
    private String itemSeq;

    @Schema(description = "제약회사 이름", example = "제약회사 이름")
    private String entpName;

    @Schema(description = "DrugSummary의 약물 이름", example = "타이레놀")
    private String drugSummaryItemName;

    @Builder
    public AllRoutineResponse(Long drugInfoId, String drugName, String dosage, String itemSeq, String entpName, String drugSummaryItemName) {
        this.drugInfoId = drugInfoId;
        this.drugName = drugName;
        this.dosage = dosage;
        this.itemSeq = itemSeq;
        this.entpName = entpName;
        this.drugSummaryItemName = drugSummaryItemName;
    }


}


