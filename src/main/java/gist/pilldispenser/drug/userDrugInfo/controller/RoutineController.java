package gist.pilldispenser.drug.userDrugInfo.controller;


import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.RoutineRequestDto;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.drug.userDrugInfo.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;
    private final RoutineRepository routineRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createRoutine(
            @RequestBody RoutineRequestDto routineRequestDto,
            @AuthenticationPrincipal UsersDetails userDetails) {

        Long userId = userDetails.getId();

        routineService.createRoutine(userId, routineRequestDto);

        return ResponseEntity.ok("루틴이 성공적으로 저장되었습니다.");
    }


    // 모든 루틴 조회 (사용자별)
    @GetMapping
    public ResponseEntity<List<Routine>> getRoutines(@AuthenticationPrincipal UsersDetails userDetails) {
        Long userId = userDetails.getId();
        List<Routine> routines = routineService.getRoutinesByUserId(userId);
        return ResponseEntity.ok(routines);
    }

    // 특정 루틴 조회
    @GetMapping("/{routineId}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable(name = "routineId") Long routineId) {
        return routineService.getRoutineById(routineId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 루틴 업데이트
    @PutMapping("/{routineId}")
    public ResponseEntity<Routine> updateRoutine(
            @PathVariable(name = "routineId") Long routineId,
            @RequestBody RoutineRequestDto routineRequestDto) {

        Routine updatedRoutine = routineService.updateRoutine(routineId, routineRequestDto);
        return ResponseEntity.ok(updatedRoutine);
    }

    @Transactional
    public void deleteRoutine(Long routineId) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 루틴을 찾을 수 없습니다."));

        routineRepository.delete(routine);
    }


}