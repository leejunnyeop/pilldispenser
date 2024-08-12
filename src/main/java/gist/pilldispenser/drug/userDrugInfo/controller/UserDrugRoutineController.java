package gist.pilldispenser.drug.userDrugInfo.controller;

import gist.pilldispenser.common.security.UsersDetails;
import gist.pilldispenser.drug.userDrugInfo.service.UserDrugRoutineService;
import gist.pilldispenser.drug.userDrugInfo.domain.dto.UserDrugRoutineResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "User Medications", description = "사용자 복용 중인 약물 관리 API")
@RestController
@RequestMapping("/api/user-drugs")
@RequiredArgsConstructor
public class UserDrugRoutineController {

    private final UserDrugRoutineService userDrugRoutineService;

    @Operation(summary = "모든 복용 중인 약 조회", description = "현재 로그인된 사용자의 모든 복용 중인 약과 관련된 루틴 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "복용 중인 약물 정보가 성공적으로 조회되었습니다."),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @GetMapping()
    public ResponseEntity<List<UserDrugRoutineResponse>> getUserDrugRoutines(
            @AuthenticationPrincipal UsersDetails usersDetails) {
        Long userId = usersDetails.getId();
        List<UserDrugRoutineResponse> routines = userDrugRoutineService.getUserDrugRoutines(userId);
        return ResponseEntity.ok(routines);
    }
}
