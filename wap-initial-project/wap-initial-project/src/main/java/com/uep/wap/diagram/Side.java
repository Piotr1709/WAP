package com.uep.wap.diagram;

public class Side {
    private int sideId;
    private String type;
    private Player player;
    private Team team;

    public boolean isTeam() {
        return "team".equals(type);
    }

    public boolean isSinglePlayer() {
        return "single".equals(type);
    }
}