package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoutineResponse {

    private List<Long> routineIds;
}
