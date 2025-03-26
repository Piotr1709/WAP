package com.uep.wap.diagram;

import java.text.DateFormat;

public class Match {
    private int matchId;
    private Tournament tournament;
    private Side side1;
    private Side side2;
    private Referee referee;
    private Stadium stadium;
    private String date;
    private String time;
    private String status;
    private Score score;


    public void updateScore(Score newScore) {
        this.score = newScore;
        // Update tournament statistics
    }

    public void assignReferee(Referee ref) {
        this.referee = ref;
    }

    public void setSchedule(String newDate, String newTime) {
        this.date = newDate;
        this.time = newTime;
    }

    public boolean involvePlayer(Player player) {
        // Check if the given player is involved in this match
        return false;
    }
}