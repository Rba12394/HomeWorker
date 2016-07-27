package com.rfmicron.homeworkerstruct;

import java.util.Date;

/**
 * Created by Blake on 6/8/2015.
 */
public class Assignment {
    public String course;
    public Date dueDate;
    public String notes;

    public Assignment(String myCourse, Date myDueDate, String myNotes){
        course = myCourse;
        dueDate = myDueDate;
        notes = myNotes;
    }

    public String getCourse(){
        return course;
    }
    public Date getDueDate(){
        return dueDate;
    }
    public String getNotes(){
        return notes;
    }
    public void setNotes(String note){
        notes = note;
    }
    public void setDueDate(Date due){
        dueDate = due;
    }

}
