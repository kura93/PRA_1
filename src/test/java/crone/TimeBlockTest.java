package crone;

import org.junit.Test;

import static org.junit.Assert.*;


public class TimeBlockTest {
    @Test
    public void isIsCurrentBlock() throws Exception {
       TimeBlock tb = new TimeBlock(7,30,8,45, "sth");
       assertEquals(false, tb.isThisCurrentBlock());
    }
    @Test
    public void isIsCurrentBlock2() throws Exception {
        TimeBlock tb = new TimeBlock(7,30,13,45, "sth");
        assertEquals(true, tb.isThisCurrentBlock());
    }
    @Test
    public void minutesToEnd() throws Exception {
        TimeBlock tb = new TimeBlock(11,30,13,15,"typ");
        assertEquals(true, tb.minutesToEnd(13,13)==2);
    }

}