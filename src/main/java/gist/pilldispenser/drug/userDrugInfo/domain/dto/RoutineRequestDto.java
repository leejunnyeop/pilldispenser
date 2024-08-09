package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class RoutineRequestDto {

    @Schema(description = "복용 시간", example = "08:00")
    private LocalTime time;  // 복용 시간

    @Schema(description = "복용 시 1회 복용량", example = "2")
    private int dosagePerTake;  // 복용 시 1회 복용량

    @Schema(description = "하루 복용 횟수", example = "3")
    private int dailyDosage;  // 하루 복용 횟수

    @Schema(description = "루틴 활성화 여부", example = "true")
    private boolean isActive;  // 루틴 활성화 여부

    @Schema(description = "약물 고유 번호 (itemSeq)", example = "123456789")
    private String itemSeq;  // 약물 고유 번호
}