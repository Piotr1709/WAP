package com.uep.wap.diagram;

public class Statistics {
    private int statisticsId;
    private int entityId;
    private String entityType;
    private int tournamentId;
    private int matchesPlayed;
    private int matchesWon;
    private int setsWon;
    private int gamesWon;

    public float calculateWinPercentage() {
        return (float) matchesWon / matchesPlayed * 100;
    }

    public void updateAfterMatch(Match match) {
        // Update statistics based on match result
    }

    public boolean getTournamentId() {
    }
}
