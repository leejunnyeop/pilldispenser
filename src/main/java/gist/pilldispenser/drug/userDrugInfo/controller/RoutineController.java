package gist.pilldispenser.drug.userDrugInfo.controller;


import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.RoutineRequestDto;
import gist.pilldispenser.drug.userDrugInfo.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping("/create")
    public ResponseEntity<String> createRoutine(
            @RequestBody RoutineRequestDto routineRequestDto,
            @AuthenticationPrincipal UsersDetails userDetails) {

        Long userId = userDetails.getId();

        routineService.createRoutine(userId, routineRequestDto);

        return ResponseEntity.ok("루틴이 성공적으로 저장되었습니다.");
    }

}