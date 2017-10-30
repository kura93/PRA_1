package crone;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;


public class TimeBlockTest {
    @Test
    public void isIsCurrentBlock() throws Exception {
       Date date = new Date();
       TimeBlock tb = new TimeBlock(7,30,date.getHours()-1,00, "sth");
       assertEquals(false, tb.isThisCurrentBlock());
    }
    @Test
    public void isIsCurrentBlock2() throws Exception {
        Date date = new Date();
        TimeBlock tb = new TimeBlock(0,0,date.getHours(),date.getMinutes()+1, "sth");
        assertEquals(true, tb.isThisCurrentBlock());
    }

    @Test
    public void minutesToEnd() throws Exception {
        TimeBlock tb = new TimeBlock(11,30,13,15,"typ");
        assertEquals(true, tb.minutesToEnd(13,13)==2);
    }
    @Test
    public void minutesToEnd1() throws Exception {
        TimeBlock tb = new TimeBlock(11,30,13,15,"typ");
        assertEquals(false, tb.minutesToEnd(13,13)==1);
    }

}