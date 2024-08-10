package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineRequestDto {

    @Schema(description = "복용 시간", example = "08:00")
    @JsonFormat(pattern = "HH:mm")
    private String time;  // 복용 시간

    @Schema(description = "복용 시 1회 복용량", example = "2")
    private Integer dosagePerTake;  // 복용 시 1회 복용량

    @Schema(description = "하루 복용 횟수", example = "3")
    private Integer dailyDosage;  // 하루 복용 횟수

    @Schema(description = "루틴 활성화 여부", example = "true")
    private Boolean isActive;  // 루틴 활성화 여부

    @Schema(description = "약물 고유 번호 (itemSeq)", example = "123456789")
    private String itemSeq;  // 약물 고유 번호
}