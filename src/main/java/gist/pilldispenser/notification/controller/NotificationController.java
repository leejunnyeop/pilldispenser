package gist.pilldispenser.notification.controller;

import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.notification.domain.NotificationRequest;
import gist.pilldispenser.notification.service.CustomScheduleService;
import gist.pilldispenser.notification.service.NotificationHelper;
import gist.pilldispenser.users.domain.entity.Users;
import gist.pilldispenser.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
