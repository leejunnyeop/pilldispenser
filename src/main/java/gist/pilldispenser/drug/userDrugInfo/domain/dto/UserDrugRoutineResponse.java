package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Builder
public class UserDrugRoutineResponse {

    private String drugName;       // 약물 이름
    private String drugType;       // 약물 입력 유형: "direct" 또는 "search"
    private LocalTime time;        // 복용 시간
    private int dosagePerTake;     // 복용 시 1회 복용량
    private int dailyDosage;       // 하루 복용 횟수
    private DayOfWeek dayOfWeek;   // 복용 요일
    private boolean isActive;      // 루틴 활성화 여부
}