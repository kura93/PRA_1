package crone;

import Utils.FileSaver;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;



public class SaveToFileJob implements org.quartz.Job {
    public SaveToFileJob(){}

    public void execute(JobExecutionContext context) throws JobExecutionException {
        FileSaver fs = FileSaver.getFileSaver();
        fs.printToFile();
    }
}