package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import gist.pilldispenser.drug.userDrugInfo.domain.enums.DayType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Builder
public class UserDrugRoutineResponse {

    private String drugName;       // 약물 이름
    private int dosagePerTake;     // 복용 시 1회 복용량

    private DayType days;


}