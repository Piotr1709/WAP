package com.uep.wap.diagram;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private float winPercentage;
    
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Statistics> statistics;
    
    @ManyToMany(mappedBy = "players")
    private List<Tournament> tournaments;
    
    public Player() {
        // Default constructor required by JPA
    }
    
    public Player(Long userId, String username, String email, String password,
                  Long playerId, String firstName, String lastName) {
        super(userId, username, email, password);
        this.id = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.statistics = new ArrayList<>();
        this.tournaments = new ArrayList<>();
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public float getWinPercentage() {
        return winPercentage;
    }
    
    public void setWinPercentage(float winPercentage) {
        this.winPercentage = winPercentage;
    }
    
    public List<Statistics> getStatistics() {
        return statistics;
    }
    
    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }
    
    public List<Tournament> getTournaments() {
        return tournaments;
    }
    
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
    
    // Business methods
    public void registerForTournament(Tournament tournament) {
        tournament.registerPlayer(this);
        if (!tournaments.contains(tournament)) {
            tournaments.add(tournament);
        }
    }
    
    public List<Match> viewSchedule(Tournament tournament) {
        return tournament.getPlayerSchedule(this);
    }
    
    public Statistics checkPerformanceStats(Tournament tournament) {
        return findStatisticsForTournament(tournament);
    }
    
    private Statistics findStatisticsForTournament(Tournament tournament) {
        return statistics.stream()
                .filter(stat -> stat.getTournament().getId().equals(tournament.getId()))
                .findFirst()
                .orElse(null);
    }
}
