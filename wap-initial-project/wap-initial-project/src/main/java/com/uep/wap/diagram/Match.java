package com.uep.wap.diagram;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "match")
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "match_player",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players;
    
    @ManyToOne
    @JoinColumn(name = "referee_id")
    private Referee referee;
    
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
    
    private String date;
    private String time;
    private String status;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id")
    private Score score;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "match_side",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "side_id"))
    private List<Side> sides;
    
    public Match() {
        // Default constructor required by JPA
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Tournament getTournament() {
        return tournament;
    }
    
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public Referee getReferee() {
        return referee;
    }
    
    public void setReferee(Referee referee) {
        this.referee = referee;
    }
    
    public Stadium getStadium() {
        return stadium;
    }
    
    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Score getScore() {
        return score;
    }
    
    public void setScore(Score score) {
        this.score = score;
    }
    
    public List<Side> getSides() {
        return sides;
    }
    
    public void setSides(List<Side> sides) {
        this.sides = sides;
    }
    
    // Business methods
    public void updateScore(Score newScore) {
        this.score = newScore;
        // Update tournament statistics
        if (tournament != null) {
            tournament.updateBrackets();
        }
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
        if (players != null && players.contains(player)) {
            return true;
        }
        
        // Check if player is part of any side in this match
        if (sides != null) {
            for (Side side : sides) {
                if (side.containsPlayer(player)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void setComments(String comments) {
    }

    public Side getSide1() {
        return null;
    }

    public Side getSide2() {
        return null;
    }
}
