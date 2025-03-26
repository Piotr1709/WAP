package com.uep.wap.diagram;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tournament {
    private int tournamentId;
    private String name;
    private String startDate;
    private String endDate;
    private String format;
    private String type;
    private String location;
    private int maxPlayers;
    private String status;
    private Bracket bracket;
    private List<Player> players;
    private List<Team> teams;
    private List<Match> matches;

    public Tournament(String name, String startDate, String endDate,
                      String format, String type, String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.format = format;
        this.type = type;
        this.location = location;
        this.players = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void registerPlayer(Player player) {
        if (type.equals("singles")) {
            players.add(player);
        }
    }

    public void registerTeam(Team team) {
        if (type.equals("doubles")) {
            teams.add(team);
        }
    }

    public List<Match> getPlayerSchedule(Player player) {
        return matches.stream()
                .filter(match -> match.involvePlayer(player))
                .collect(Collectors.toList());
    }

    public boolean isSingles() {
        return "singles".equals(type);
    }

    public boolean isDoubles() {
        return "doubles".equals(type);
    }

    public void createDraws() {
        // Logic to create tournament draws
    }

    public void updateBrackets() {
        // Logic to update tournament brackets
    }

    public void generateSchedule() {
        // Logic to generate match schedules
    }

    public boolean getTournamentId() {
        return tournamentId == 0;
    }
}
