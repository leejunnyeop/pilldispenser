package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class RoutineRequestDto {

    private LocalTime time;  // 복용 시간
    private int dosagePerTake;  // 복용 시 1회 복용량
    private int dailyDosage;  // 하루 복용 횟수
    private boolean isActive;  // 루틴 활성화 여부
    private String itemSeq;

}