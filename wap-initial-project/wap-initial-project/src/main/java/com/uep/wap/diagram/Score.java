package com.uep.wap.diagram;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "scores")
public class Score {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;
    
    @OneToOne
    @JoinColumn(name = "match_id")
    private Match match;
    
    @Column(name = "set1_scores")
    private String set1ScoresJson;
    
    @Column(name = "set2_scores")
    private String set2ScoresJson;
    
    @Column(name = "set3_scores")
    private String set3ScoresJson;
    
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Side winner;
    
    // Default constructor required by JPA
    public Score() {
    }
    
    public Score(Match match) {
        this.match = match;
    }
    
    public Long getScoreId() {
        return scoreId;
    }
    
    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }
    
    public Match getMatch() {
        return match;
    }
    
    public void setMatch(Match match) {
        this.match = match;
    }
    
    @Transient
    public int[] getSet1Scores() {
        return deserializeScores(set1ScoresJson);
    }
    
    public void setSet1Scores(int[] set1Scores) {
        this.set1ScoresJson = serializeScores(set1Scores);
    }
    
    @Transient
    public int[] getSet2Scores() {
        return deserializeScores(set2ScoresJson);
    }
    
    public void setSet2Scores(int[] set2Scores) {
        this.set2ScoresJson = serializeScores(set2Scores);
    }
    
    @Transient
    public int[] getSet3Scores() {
        return deserializeScores(set3ScoresJson);
    }
    
    public void setSet3Scores(int[] set3Scores) {
        this.set3ScoresJson = serializeScores(set3Scores);
    }
    
    public Side getWinner() {
        return winner;
    }
    
    public void setWinner(Side winner) {
        this.winner = winner;
    }
    
    // Helper methods to convert between arrays and database-friendly representation
    private String serializeScores(int[] scores) {
        if (scores == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scores.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(scores[i]);
        }
        return sb.toString();
    }
    
    private int[] deserializeScores(String scoresJson) {
        if (scoresJson == null || scoresJson.isEmpty()) {
            return new int[0];
        }
        String[] parts = scoresJson.split(",");
        int[] scores = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            scores[i] = Integer.parseInt(parts[i]);
        }
        return scores;
    }
    
    public void calculateWinner() {
        // Logic to determine match winner based on set scores
        int side1Sets = 0;
        int side2Sets = 0;
        
        int[] set1 = getSet1Scores();
        int[] set2 = getSet2Scores();
        int[] set3 = getSet3Scores();
        
        // Count sets won by each side
        if (set1.length == 2 && set1[0] > set1[1]) {
            side1Sets++;
        } else if (set1.length == 2) {
            side2Sets++;
        }
        
        if (set2.length == 2 && set2[0] > set2[1]) {
            side1Sets++;
        } else if (set2.length == 2) {
            side2Sets++;
        }
        
        if (set3.length == 2 && set3[0] > set3[1]) {
            side1Sets++;
        } else if (set3.length == 2) {
            side2Sets++;
        }
        
        // Set winner based on sets won
        if (side1Sets > side2Sets) {
            this.winner = match.getSide1();
        } else if (side2Sets > side1Sets) {
            this.winner = match.getSide2();
        }
        // If equal, no winner is set
    }
    
    public void updateStats() {
        // Update player/team statistics after match
        if (winner != null && match != null) {
            if (winner.isTeam()) {
                // Update team statistics
                Team winningTeam = winner.getTeam();
                if (winningTeam != null) {
                    winningTeam.incrementWins();
                }
                
                // Update losing team
                Side loser = (winner.equals(match.getSide1())) ? match.getSide2() : match.getSide1();
                if (loser != null && loser.isTeam()) {
                    Team losingTeam = loser.getTeam();
                    if (losingTeam != null) {
                        losingTeam.incrementLosses();
                    }
                }
            } else if (winner.isSinglePlayer()) {
                // Update player statistics
                Player winningPlayer = winner.getPlayer();
                if (winningPlayer != null) {
                    winningPlayer.incrementWins();
                }
                
                // Update losing player
                Side loser = (winner.equals(match.getSide1())) ? match.getSide2() : match.getSide1();
                if (loser != null && loser.isSinglePlayer()) {
                    Player losingPlayer = loser.getPlayer();
                    if (losingPlayer != null) {
                        losingPlayer.incrementLosses();
                    }
                }
            }
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(scoreId, score.scoreId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(scoreId);
    }
    
    @Override
    public String toString() {
        return "Score{" +
                "scoreId=" + scoreId +
                ", set1=" + Arrays.toString(getSet1Scores()) +
                ", set2=" + Arrays.toString(getSet2Scores()) +
                ", set3=" + Arrays.toString(getSet3Scores()) +
                ", winner=" + (winner != null ? winner.getSideId() : "none") +
                '}';
    }
}
