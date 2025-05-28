package com.uep.wap.diagram;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "teams")
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Player player1;
    
    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Player player2;
    
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Statistics> statistics = new ArrayList<>();
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "team_tournament",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "tournament_id")
    )
    private List<Tournament> tournaments = new ArrayList<>();
    
    @OneToMany(mappedBy = "team")
    private List<Side> sides = new ArrayList<>();
    
    private int wins;
    private int losses;
    
    // Default constructor required by JPA
    public Team() {
    }
    
    public Team(String name, Player player1, Player player2) {
        this.name = name;
        this.player1 = player1;
        this.player2 = player2;
        this.wins = 0;
        this.losses = 0;
    }
    
    public Long getTeamId() {
        return teamId;
    }
    
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    
    public Long getId() {
        return teamId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Player getPlayer1() {
        return player1;
    }
    
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    
    public Player getPlayer2() {
        return player2;
    }
    
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
    public List<Statistics> getStatistics() {
        return statistics;
    }
    
    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }
    
    public void addStatistics(Statistics statistic) {
        statistics.add(statistic);
    }
    
    public List<Tournament> getTournaments() {
        return tournaments;
    }
    
    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }
    
    public List<Side> getSides() {
        return sides;
    }
    
    public void setSides(List<Side> sides) {
        this.sides = sides;
    }
    
    public int getWins() {
        return wins;
    }
    
    public void setWins(int wins) {
        this.wins = wins;
    }
    
    public int getLosses() {
        return losses;
    }
    
    public void setLosses(int losses) {
        this.losses = losses;
    }
    
    public void incrementWins() {
        this.wins++;
    }
    
    public void incrementLosses() {
        this.losses++;
    }
    
    public void registerForTournament(Tournament tournament) {
        if (!tournaments.contains(tournament)) {
            tournaments.add(tournament);
            tournament.registerTeam(this);
            
            // Create statistics entry for this tournament
            Statistics stats = new Statistics("TEAM", this.teamId, tournament);
            this.addStatistics(stats);
        }
    }
    
    public List<Match> viewSchedule(Tournament tournament) {
        if (!tournaments.contains(tournament)) {
            return new ArrayList<>();
        }
        
        return tournament.getMatches().stream()
            .filter(match -> {
                Side side1 = match.getSide1();
                Side side2 = match.getSide2();
                
                return (side1 != null && side1.isTeam() && side1.getTeam().equals(this)) ||
                       (side2 != null && side2.isTeam() && side2.getTeam().equals(this));
            })
            .collect(Collectors.toList());
    }
    
    public Statistics getStatisticsForTournament(Tournament tournament) {
        return statistics.stream()
            .filter(s -> s.getTournament().equals(tournament))
            .findFirst()
            .orElse(null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(teamId, team.teamId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(teamId);
    }
    
    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", name='" + name + '\'' +
                ", player1=" + (player1 != null ? player1.getName() : "null") +
                ", player2=" + (player2 != null ? player2.getName() : "null") +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
