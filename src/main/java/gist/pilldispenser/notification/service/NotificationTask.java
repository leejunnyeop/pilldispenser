package gist.pilldispenser.notification.service;

import gist.pilldispenser.drug.userDrugInfo.domain.entity.Routine;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class NotificationTask implements Runnable{

    private final NotificationHelper notificationHelper;
    private final Routine routine;

    public NotificationTask(NotificationHelper notificationHelper, Routine routine) {
        this.notificationHelper = notificationHelper;
        this.routine = routine;
    }

    @Override
    public void run() {
        try {
            String response = notificationHelper.sendNotification(routine);
            log.info("kakao message sent: {}", response);
        } catch (IOException e) {
            log.error("kakao message canceled: {}", e.getMessage());
        }
    }
}
