package com.uep.wap.diagram;

public class Stadium {
    private int stadiumId;
    private String name;
    private String location;
    private int capacity;
    private String surface;
    private boolean isAvailable;

    public boolean checkAvailability() {
        return isAvailable;
    }
}