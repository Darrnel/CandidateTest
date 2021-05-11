package com.nebu.candidatetest.Model;

public class Interviewer {
    int ID;
    String name;
    float hourly_rate;
    String supervisorName;

    public Interviewer(int ID, String name, float hourly_rate, String supervisorName) {
        this.ID = ID;
        this.name = name;
        this.hourly_rate = hourly_rate;
        this.supervisorName = supervisorName;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public float getHourly_rate() {
        return hourly_rate;
    }

    public String getSupervisorName() {
        return supervisorName;
    }
}
