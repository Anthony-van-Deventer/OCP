package study.ocp;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        //set the next run to the time that you want it to run, in this case 5 in the morning, will probably have to be in a quiet time
        LocalDateTime nextRun = now.withHour(5).withMinute(0).withSecond(0);
        if(now.compareTo(nextRun) > 0)
            //if now is after the next run, set the next run time to the next day
            nextRun = nextRun.plusDays(1);

        //initial delay will be the time in seconds between now and the next run
        long initialDelay = Duration.between(now, nextRun).getSeconds();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(new MyRunnable(),
                initialDelay,
                //seconds in a day
                TimeUnit.DAYS.toSeconds(1),
                TimeUnit.SECONDS);
    }
}
