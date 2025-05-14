package com.uep.wap.diagram;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "statistics")
public class Statistics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statisticsId;
    
    @Column(name = "entity_id")
    private Long entityId;
    
    @Column(name = "entity_type")
    private String entityType;
    
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    
    @Column(name = "matches_played")
    private int matchesPlayed;
    
    @Column(name = "matches_won")
    private int matchesWon;
    
    @Column(name = "sets_won")
    private int setsWon;
    
    @Column(name = "games_won")
    private int gamesWon;
    
    @ManyToMany
    @JoinTable(
        name = "statistics_match",
        joinColumns = @JoinColumn(name = "statistics_id"),
        inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Match> matches = new ArrayList<>();
    
    // Default constructor required by JPA
    public Statistics() {
    }
    
    public Statistics(String entityType, Long entityId, Tournament tournament) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.tournament = tournament;
        this.matchesPlayed = 0;
        this.matchesWon = 0;
        this.setsWon = 0;
        this.gamesWon = 0;
    }
    
    public Long getStatisticsId() {
        return statisticsId;
    }
    
    public void setStatisticsId(Long statisticsId) {
        this.statisticsId = statisticsId;
    }
    
    public Long getEntityId() {
        return entityId;
    }
    
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
    
    public String getEntityType() {
        return entityType;
    }
    
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    
    public Tournament getTournament() {
        return tournament;
    }
    
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public Long getTournamentId() {
        return tournament != null ? tournament.getTournamentId() : null;
    }
    
    public int getMatchesPlayed() {
        return matchesPlayed;
    }
    
    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
    
    public int getMatchesWon() {
        return matchesWon;
    }
    
    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }
    
    public int getSetsWon() {
        return setsWon;
    }
    
    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }
    
    public int getGamesWon() {
        return gamesWon;
    }
    
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }
    
    public List<Match> getMatches() {
        return matches;
    }
    
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    
    public void addMatch(Match match) {
        if (!matches.contains(match)) {
            matches.add(match);
        }
    }
    
    public void removeMatch(Match match) {
        matches.remove(match);
    }
    
    public float calculateWinPercentage() {
        if (matchesPlayed == 0) {
            return 0.0f;
        }
        return (float) matchesWon / matchesPlayed * 100;
    }
    
    public void updateAfterMatch(Match match) {
        // Add the match to this statistics record
        addMatch(match);
        
        // Update match count
        matchesPlayed++;
        
        // Get the score from the match
        Score score = match.getScore();
        if (score == null) {
            return;
        }
        
        // Determine if this entity won the match
        Side winner = score.getWinner();
        if (winner == null) {
            return;
        }
        
        boolean isWinner = false;
        
        // Check if this entity is the winner
        if ("PLAYER".equals(entityType)) {
            if (winner.isSinglePlayer() && winner.getPlayer().getId().equals(entityId)) {
                isWinner = true;
            }
        } else if ("TEAM".equals(entityType)) {
            if (winner.isTeam() && winner.getTeam().getId().equals(entityId)) {
                isWinner = true;
            }
        }
        
        // Update wins if this entity won
        if (isWinner) {
            matchesWon++;
        }
        
        // Update sets and games won
        int[] set1Scores = score.getSet1Scores();
        int[] set2Scores = score.getSet2Scores();
        int[] set3Scores = score.getSet3Scores();
        
        updateSetsAndGames(set1Scores);
        updateSetsAndGames(set2Scores);
        updateSetsAndGames(set3Scores);
    }
    
    private void updateSetsAndGames(int[] setScores) {
        if (setScores == null || setScores.length != 2) {
            return;
        }
        
        int entityIndex = -1;
        
        // Determine which index in the scores array corresponds to this entity
        Match match = new Match();
        if ("PLAYER".equals(entityType)) {
            // Logic to determine if player is side1 or side2
            Side side1 = match.getSide1();
            if (side1 != null && side1.isSinglePlayer() && 
                side1.getPlayer().getId().equals(entityId)) {
                entityIndex = 0;
            } else {
                entityIndex = 1;
            }
        } else if ("TEAM".equals(entityType)) {
            // Logic to determine if team is side1 or side2
            Side side1 = match.getSide1();
            if (side1 != null && side1.isTeam() && 
                side1.getTeam().getId().equals(entityId)) {
                entityIndex = 0;
            } else {
                entityIndex = 1;
            }
        }
        
        if (entityIndex >= 0) {
            // Add games won in this set
            gamesWon += setScores[entityIndex];
            
            // Check if this entity won the set
            if ((entityIndex == 0 && setScores[0] > setScores[1]) ||
                (entityIndex == 1 && setScores[1] > setScores[0])) {
                setsWon++;
            }
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(statisticsId, that.statisticsId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(statisticsId);
    }
    
    @Override
    public String toString() {
        return "Statistics{" +
                "statisticsId=" + statisticsId +
                ", entityType='" + entityType + '\'' +
                ", entityId=" + entityId +
                ", tournamentId=" + getTournamentId() +
                ", matchesPlayed=" + matchesPlayed +
                ", matchesWon=" + matchesWon +
                ", setsWon=" + setsWon +
                ", gamesWon=" + gamesWon +
                ", winPercentage=" + calculateWinPercentage() + "%" +
                '}';
    }
}
