package crone;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SchoolJob implements org.quartz.Job {

    TimeBlock blocks[];

    public SchoolJob(){
        this.blocks = new TimeBlock[11];
        blocks[0] = new TimeBlock(8,15,9,45,"zajęcia");
        blocks[1] = new TimeBlock(10,0,11,30,"zajęcia");
        blocks[2] = new TimeBlock(11,45,13,15,"zajęcia");
        blocks[3] = new TimeBlock(13,45,15,15,"zajęcia");
        blocks[4] = new TimeBlock(15,30,17,0,"zajęcia");
        blocks[5] = new TimeBlock(17,15,18,45,"zajęcia");
        blocks[6] = new TimeBlock(9,46,9,59,"przerwa");
        blocks[7] = new TimeBlock(11,31,11,44,"przerwa");
        blocks[8] = new TimeBlock(13,16,13,44,"przerwa");
        blocks[9] = new TimeBlock(15,16,15,29,"przerwa");
        blocks[10] = new TimeBlock(17,1,17,14,"przerwa");
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        TimeBlock currentBlock = this.getCurrentTimeBlock();
        System.out.println("dzialam");
        if(currentBlock == null) {
            System.out.println("Uczelnia zamknięta");
        } else {
            System.out.println("blok: " + currentBlock.getType() +" minut: " + currentBlock.minutesToEnd());
        }
    }

    private TimeBlock getCurrentTimeBlock() {
        for(int i=0; i<this.blocks.length; i++) {
            if(this.blocks[i].isThisCurrentBlock()) {
                return this.blocks[i];
            }
        }
        return null;
    }
}