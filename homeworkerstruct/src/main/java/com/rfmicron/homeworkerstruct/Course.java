package com.rfmicron.homeworkerstruct;

/**
 * Created by Blake on 6/8/2015.
 */
public class Course {
    public String name;
    public int startTime;
    public int endTime;
    public String meetingDays;
    public String classNum;


    public Course(String myName, int myStartTime, int myEndTime, String myMeetingDays, String classNum){
        name = myName;
        startTime = myStartTime;
        endTime = myEndTime;
        meetingDays = myMeetingDays;
    }

    public void setName(String newValue){
        name = newValue;
    }
    public void setStartTime(int newValue){
        startTime = newValue;
    }
    public void setEndTime(int newValue){
        endTime = newValue;
    }
    public void setClassNume(String newValue){
        classNum = newValue;
    }
    public void setMeetingDays(String days){
        meetingDays = days;
    }

    public String getName(){
        return name;
    }
    public int getStartTime(){
        return startTime;
    }
    public int getEndTime(){
        return endTime;
    }
    public String getMeetingDays(){
        return meetingDays;
    }
}
