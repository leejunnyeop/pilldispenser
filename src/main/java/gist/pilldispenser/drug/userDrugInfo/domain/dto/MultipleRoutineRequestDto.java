package gist.pilldispenser.drug.userDrugInfo.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleRoutineRequestDto {

    @Schema(description = "정기복용 루틴 설정 요청(리스트)")
    private List<RoutineRequestDto> routines;
}
