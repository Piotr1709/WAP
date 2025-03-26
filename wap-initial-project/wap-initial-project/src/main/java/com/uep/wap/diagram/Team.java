package com.uep.wap.diagram;

import java.util.List;

public class Team {
    private int teamId;
    private String name;
    private Player player1;
    private Player player2;
    private List<Statistics> statistics;

    public void registerForTournament(Tournament tournament) {
        tournament.registerTeam(this);
    }

    public List<Match> viewSchedule(Tournament tournament) {
        // Similar to player's view schedule method
        return null;
    }
}
