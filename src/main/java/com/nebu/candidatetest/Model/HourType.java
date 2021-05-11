package com.nebu.candidatetest.Model;

public class HourType {
    int ID;
    String name;
    float ratio;

    public HourType(int ID, String name, float ratio) {
        this.ID = ID;
        this.name = name;
        this.ratio = ratio;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public float getRatio() {
        return ratio;
    }
}
