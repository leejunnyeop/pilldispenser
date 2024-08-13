package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoutineResponse {

    private Long routineId;
    private String dailyDosage;
    private String dosagePerTake;
    private String time;
    private String day;
}
