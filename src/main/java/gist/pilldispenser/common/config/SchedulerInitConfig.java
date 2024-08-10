package gist.pilldispenser.common.config;

import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import gist.pilldispenser.drug.userDrugInfo.repository.RoutineRepository;
import gist.pilldispenser.notification.service.CustomScheduleService;
import gist.pilldispenser.notification.service.NotificationHelper;
import gist.pilldispenser.notification.service.NotificationTask;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SchedulerInitConfig {

    private final CustomScheduleService customScheduleService;
    private final RoutineRepository routineRepository;
    private final NotificationHelper notificationHelper;

    // DB에서 약 복용 루틴을 가져옴
    @Bean
    public ApplicationRunner initializeSchedulers(){
        return args -> {
            List<Routine> routines = routineRepository.findAll();
            for (Routine routine : routines) {
                if (routine.isActive()){
                    NotificationTask task = new NotificationTask(notificationHelper, routine);
                    String taskKey = "schedule-"+routine.getId();
                    customScheduleService.scheduleNotification(taskKey, task, notificationHelper.getCronExpression(routine));
                }
            }
        };
    }
}