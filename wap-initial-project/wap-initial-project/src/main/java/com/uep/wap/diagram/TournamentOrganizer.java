package com.uep.wap.diagram;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tournament_organizers")
public class TournamentOrganizer extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizerId;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private List<Tournament> tournaments = new ArrayList<>();
    
    // Default constructor required by JPA
    public TournamentOrganizer() {
        super();
    }
    
    public TournamentOrganizer(String username, String email, String password,
                              String firstName, String lastName) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Long getOrganizerId() {
        return organizerId;
    }
    
    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public List<Tournament> getTournaments() {
        return tournaments;
    }
    
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
    
    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
        tournament.setOrganizer(this);
    }
    
    public void removeTournament(Tournament tournament) {
        tournaments.remove(tournament);
        tournament.setOrganizer(null);
    }
    
    public Tournament createTournament(String name, String startDate, String endDate,
                                      String format, String type, String location) {
        Tournament tournament = new Tournament(name, startDate, endDate, format, type, location);
        this.addTournament(tournament);
        return tournament;
    }
    
    public void managePlayers(Tournament tournament) {
        // Validate that the tournament is organized by this organizer
        if (!tournaments.contains(tournament)) {
            throw new IllegalArgumentException("Tournament not organized by this organizer");
        }
        
        // Manage players in the tournament
        // Implementation would depend on specific requirements
    }
    
    public void assignReferees(Match match, Referee referee) {
        // Validate that the match belongs to a tournament organized by this organizer
        Tournament tournament = match.getTournament();
        if (tournament == null || !tournaments.contains(tournament)) {
            throw new IllegalArgumentException("Match not in a tournament organized by this organizer");
        }
        
        match.assignReferee(referee);
    }
    
    public void updateScores(Match match, Score score) {
        // Validate that the match belongs to a tournament organized by this organizer
        Tournament tournament = match.getTournament();
        if (tournament == null || !tournaments.contains(tournament)) {
            throw new IllegalArgumentException("Match not in a tournament organized by this organizer");
        }
        
        match.updateScore(score);
    }
    
    public Bracket generateBracket(Tournament tournament) {
        // Validate that the tournament is organized by this organizer
        if (!tournaments.contains(tournament)) {
            throw new IllegalArgumentException("Tournament not organized by this organizer");
        }
        
        return new Bracket(tournament);
    }
    
    public void adjustSchedule(Match match, String newDate, String newTime) {
        // Validate that the match belongs to a tournament organized by this organizer
        Tournament tournament = match.getTournament();
        if (tournament == null || !tournaments.contains(tournament)) {
            throw new IllegalArgumentException("Match not in a tournament organized by this organizer");
        }
        
        match.setSchedule(newDate, newTime);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TournamentOrganizer that = (TournamentOrganizer) o;
        return Objects.equals(organizerId, that.organizerId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), organizerId);
    }
    
    @Override
    public String toString() {
        return "TournamentOrganizer{" +
                "organizerId=" + organizerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
