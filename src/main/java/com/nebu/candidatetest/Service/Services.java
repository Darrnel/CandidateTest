package com.nebu.candidatetest.Service;

import com.nebu.candidatetest.Model.Interviewer;
import com.nebu.candidatetest.Model.Meeting;
import com.nebu.candidatetest.Model.Supervisor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Services {
    //individual earning by interviewers with date filter
    public static HashMap<String,Float> interviewerEarning(List<Meeting> meetingList, List<Interviewer> interviewerList, Date startDate, Date endDate){
        HashMap<String, Float> earnings = new HashMap<>();
        float currentEarning = 0;

        for(Interviewer i : interviewerList){
            earnings.put(i.getName(), (float) 0);
        }
        for (Meeting s : meetingList) {
            if (startDate.compareTo(s.getStartDate()) >= 0 && endDate.compareTo(s.getEndDate()) <= 0) {
                currentEarning = (s.getActiveTime() * s.getHourType().getRatio() * s.getInterviewer().getHourly_rate());
                earnings.replace(s.getInterviewer().getName(), earnings.get(s.getInterviewer().getName()) + currentEarning);
            }
        }
        return earnings;
    }

    //individual earning by interviewers without filter
    public static HashMap<String,Float> interviewerEarning(List<Meeting> meetingList, List<Interviewer> interviewerList){
        HashMap<String, Float> earnings = new HashMap<>();
        float currentEarning = 0;
        for(Interviewer i : interviewerList){
            earnings.put(i.getName(), (float) 0);
        }
        for (Meeting s : meetingList) {
            currentEarning = (s.getActiveTime() * s.getHourType().getRatio() * s.getInterviewer().getHourly_rate());
            earnings.replace(s.getInterviewer().getName(), earnings.get(s.getInterviewer().getName()) + currentEarning);
        }
        return earnings;
    }

    //group-by earning by supervisors for mobile view
    public static HashMap<String,Float> supervisorEarningMobile(List<Meeting> meetingList, List<Supervisor> supervisorList){
        HashMap<String, Float> earnings = new HashMap<>();
        float currentEarning = 0;
        for(Supervisor s : supervisorList){
            earnings.put(s.getName(), (float) 0);
        }
        for (Meeting m : meetingList) {
            currentEarning = (m.getActiveTime() * m.getHourType().getRatio() * m.getInterviewer().getHourly_rate());
            earnings.replace(m.getInterviewer().getSupervisorName(), earnings.get(m.getInterviewer().getSupervisorName()) + currentEarning);
        }
        return earnings;
    }

}
