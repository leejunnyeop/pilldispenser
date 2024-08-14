package gist.pilldispenser.notification.controller;

import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.notification.domain.NotificationRequest;
import gist.pilldispenser.notification.service.CustomScheduleService;
import gist.pilldispenser.notification.service.NotificationHelper;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.repository.UsersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "Notification API", description = "알림 관리 API")
@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationHelper notificationHelper;
    private final UsersRepository usersRepository;
    private final RoutineRepository routineRepository;
    private CustomScheduleService customScheduleService;

    // 아두이노에서 약 복용 확인 요청 들어오면 알림
    @Operation(summary = "약 복용 확인 알림", description = "아두이노에서 들어온 요청으로 약 복용 확인 알림을 보냅니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 알림이 전송되었습니다."),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자입니다."),
            @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다."),
            @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    })
    @PostMapping("/arduino")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request){
        Users user = usersRepository.findByHardwareNo(request.hardwareNo());
        try {
            String response = notificationHelper.sendPillNotification(user.getEmail(), request.isPillTaken());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
        }
    }
}
