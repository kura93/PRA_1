package crone;

import java.util.Date;

public class TimeBlock {
    Date start;
    Date finish;
    String type;

    public TimeBlock(int startHr, int startMin, int finishHr, int finishMin, String type) {
        this.start = new Date();
        this.start.setHours(startHr);
        this.start.setMinutes(startMin);
        this.finish = new Date();
        this.finish.setHours(finishHr);
        this.finish.setMinutes(finishMin);
        this.type = type;
    }

    public boolean isThisCurrentBlock() {
        Date date = new Date();
        if(date.after(this.start) && date.before(finish)) {
            return true;
        }
        return false;
    }

    public long minutesToEnd() {
        Date date = new Date();
        long minutes = (this.finish.getTime()-date.getTime())/60000;
        return minutes;
    }
    public long minutesToEnd(int currentHr, int currentMin) {
        Date date = new Date();
        date.setHours(currentHr);
        date.setMinutes(currentMin);
        long minutes = (this.finish.getTime()-date.getTime())/60000;
        return minutes;
    }

    public String getType() {
        return this.type;
    }
}
