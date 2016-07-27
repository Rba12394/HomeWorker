package com.rfmicron.homeworkerstruct;

import java.util.Date;

/**
 * Created by Blake on 7/1/2015.
 */
public class Semester {
    public Date startDate;
    public Date endDate;
    public String scheduleMode;
    public boolean blockdays;
    public int blockNum;

    public void Semester(){
        startDate = null;
        endDate = null;
        scheduleMode = null;
        blockdays = false;
        blockNum = 0;
    }

    public void Semester(Date start, Date end, String sched, Boolean block, int numberOfBlocks){
        startDate = start;
        endDate = end;
        scheduleMode = sched;
        blockdays = block;
        blockNum = numberOfBlocks;
    }

    public String getSchedMode(){
        return scheduleMode;
    }

    public Date getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public boolean getBlockDays(){
        return blockdays;
    }
    public int getBlockNum(){
        return blockNum;
    }

    public void setStartDate(Date d){
        startDate = d;
    }

    public void setEndDate(Date d){
        endDate = d;
    }

    public void setScheduleMode(String s){
        scheduleMode = s;
    }

    public void setBlockdays(boolean b){blockdays = b;}

    public void setBlockNum(int num){
        blockNum = num;
    }

    public String toString(){
        String result = "Semester: Runs from: " + this.getStartDate() + " to " + this.getEndDate() + " and has schedule format of " + getSchedMode();
        return result;
    }
}
