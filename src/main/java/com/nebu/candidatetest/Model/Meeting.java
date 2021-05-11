package com.nebu.candidatetest.Model;

import java.sql.Time;
import java.util.Date;

public class Meeting {
    Interviewer interviewer;
    HourType hourType;
    Date startDate;
    Date endDate;
    Time startTime;
    Time endTime;

    public Meeting(Interviewer interviewer, HourType hourType, Date startDate, Date endDate, Time startTime, Time endTime) {
        this.interviewer = interviewer;
        this.hourType = hourType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Interviewer getInterviewer() {
        return interviewer;
    }

    public HourType getHourType() {
        return hourType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getActiveTime(){
        int end = this.endTime.getHours();
        int start = this.startTime.getHours();

        return end-start;
    }
}
