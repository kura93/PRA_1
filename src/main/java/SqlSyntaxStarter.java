import Utils.FileSaver;
import crone.SaveToFileJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import sql.SqlSyntaxChecker;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;




public class SqlSyntaxStarter {
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        try {

            FileSaver fs = FileSaver.getFileSaver();

            int task = sc.nextInt();
            sc.nextLine();
            String sentence = sc.nextLine();

            SqlSyntaxChecker sqlChecker = new SqlSyntaxChecker(sentence);
            if(sqlChecker.isSyntaxCorrect()) {
                fs.addToHashMap(task,sentence);
            } else {
                System.err.print("błędy format zapytania sql");
            }

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobDetail job = newJob(SaveToFileJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(30)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.start();

            while(sc.hasNext() ) {
                try {
                    task = sc.nextInt();
                    sc.nextLine();
                    sentence = sc.nextLine();
                    sqlChecker.setSentence(sentence);
                    if (sentence.equals("exit") || sentence.equals("close")) {
                        System.out.println("koncze prace");
                        fs.close();
                        System.exit(0);
                    } else if (sqlChecker.isSyntaxCorrect()) {
                        fs.addToHashMap(task, sentence);
                    } else {
                        System.err.println("błędy format zapytania sql");
                    }
                } catch (InputMismatchException exc) {
                    System.out.println(exc);
                    sc.nextLine();
                    System.err.println("niepoprawny format numeru zapytania sql, sprobuj ponownie");
                }
            }

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}


