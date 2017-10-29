import crone.SchoolJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SchoolTimerStarter {

    public static void main(String[] args) throws InterruptedException {

        try {


            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job = newJob(SchoolJob.class)
                    .withIdentity("myJob", "group1")
                    .build();



            Trigger trigger = newTrigger()
                    .withIdentity("trigger7", "group1")

                    .withSchedule(cronSchedule("* * * ? * *"))

                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();


        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static Date prepareStartingDate() {
        Date startDate = new Date();
        startDate.setHours(19);
        startDate.setMinutes(8);
        if(startDate.after(new Date())) {
            startDate = new Date();
        }
        return startDate;
    }

    public static Date prepareEndingDate() {
        Date endDate = new Date();
        endDate.setHours(19);
        endDate.setMinutes(20);
        return endDate;
    }

}
