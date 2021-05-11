package com.nebu.candidatetest.Model;

public class Supervisor {
    int ID;
    String name;

    public Supervisor(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
