package com.uep.wap.diagram;

import java.util.ArrayList;
import java.util.List;

public class Player extends User {
    private int playerId;
    private String firstName;
    private String lastName;
    private float winPercentage;
    private List<Statistics> statistics;

    public Player(int userId, String username, String email, String password,
                  int playerId, String firstName, String lastName) {
        super(userId, username, email, password);
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.statistics = new ArrayList<>();
    }

    public void registerForTournament(Tournament tournament) {
        tournament.registerPlayer(this);
    }

    public List<Match> viewSchedule(Tournament tournament) {
        return tournament.getPlayerSchedule(this);
    }

    public Statistics checkPerformanceStats(Tournament tournament) {
        return findStatisticsForTournament(tournament);
    }

    private Statistics findStatisticsForTournament(Tournament tournament) {
        return statistics.stream()
                .filter(stat -> stat.getTournamentId() == tournament.getTournamentId())
                .findFirst()
                .orElse(null);
    }
}
