package com.uep.wap.diagram;

public class TournamentOrganizer extends User {
    private int organizerId;
    private String firstName;
    private String lastName;

    public TournamentOrganizer(int userId, String username, String email, String password,
                               int organizerId, String firstName, String lastName) {
        super(userId, username, email, password);
        this.organizerId = organizerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Tournament createTournament(String name, String startDate, String endDate,
                                       String format, String type, String location) {
        return new Tournament(name, startDate, endDate, format, type, location);
    }

    public void managePlayers(Tournament tournament) {
        // Manage players in the tournament
    }

    public void assignReferees(Match match, Referee referee) {
        match.assignReferee(referee);
    }

    public void updateScores(Match match, Score score) {
        match.updateScore(score);
    }

    public Bracket generateBracket(Tournament tournament) {
        return new Bracket(tournament);
    }

    public void adjustSchedule(Match match, String newDate, String newTime) {
        match.setSchedule(newDate, newTime);
    }
}