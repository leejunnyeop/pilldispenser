package gist.pilldispenser.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class CustomScheduleService {

    private final TaskScheduler taskScheduler;
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public ScheduledFuture<?> scheduleNotification(String key, Runnable task, String cronExpression) {
        cancelScheduledTask(key);
        ScheduledFuture<?> scheduledTask = taskScheduler.schedule(task, new CronTrigger(cronExpression));
        scheduledTasks.put(key, scheduledTask);
        return scheduledTask;
    }

    public void cancelScheduledTask(String key){
        ScheduledFuture<?> scheduledFuture = scheduledTasks.get(key);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledTasks.remove(key);
        }
    }
}
