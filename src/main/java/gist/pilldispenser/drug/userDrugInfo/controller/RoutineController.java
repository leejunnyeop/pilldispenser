package gist.pilldispenser.drug.userDrugInfo.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.MultipleRoutineRequestDto;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.RoutineResponse;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.drug.userDrugInfo.service.RoutineService;
import gist.pilldispenser.notification.service.CustomScheduleService;
import gist.pilldispenser.notification.service.NotificationHelper;
import gist.pilldispenser.notification.service.NotificationTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Routine API", description = "사용자의 약물 복용 루틴을 관리하는 API")
@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {
    private final RoutineService routineService;
    private final RoutineRepository routineRepository;
    private final NotificationHelper notificationHelper;
    private final CustomScheduleService customScheduleService;

    @Operation(summary = "루틴 생성", description = "사용자의 약물 복용 루틴을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "루틴이 성공적으로 저장되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.")
    })
    @PostMapping("/create")
    public ResponseEntity<RoutineResponse> createRoutine(
            @RequestBody MultipleRoutineRequestDto routineRequestDtos,
            @Parameter(hidden = true) @AuthenticationPrincipal UsersDetails userDetails) {

        Long userId = userDetails.getId();
        // 루틴 생성
        List<Routine> routines = routineService.createRoutine(userId, routineRequestDtos.getRoutines());

        List<Long> routineIds = new ArrayList<>();
        // 알림 등록
        for (Routine routine : routines) {
            routineIds.add(routine.getId());
            NotificationTask task = new NotificationTask(notificationHelper, routine);
            String taskKey = "schedule-" + routine.getId();
            customScheduleService.scheduleNotification(taskKey, task, notificationHelper.getCronExpression(routine));
        }

        return ResponseEntity.ok(new RoutineResponse(routineIds));
    }

    @Operation(summary = "루틴 삭제", description = "루틴 ID로 특정 루틴을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "루틴이 성공적으로 삭제되었습니다."),
            @ApiResponse(responseCode = "404", description = "루틴을 찾을 수 없습니다.")
    })
    @DeleteMapping("/{routineId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteRoutine(@PathVariable(name = "routineId") Long routineId) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 루틴을 찾을 수 없습니다."));

        routineRepository.delete(routine);
        String taskKey = "schedule-" + routineId;
        customScheduleService.cancelScheduledTask(taskKey);

    }
}
