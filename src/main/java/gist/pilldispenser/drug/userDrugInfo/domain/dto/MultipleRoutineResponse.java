package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultipleRoutineResponse {

    private List<RoutineResponse> routines;
}
