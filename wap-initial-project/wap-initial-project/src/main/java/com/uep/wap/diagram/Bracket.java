package com.uep.wap.diagram;

import java.util.List;
import javax.persistence.*;

@Entity
public class Bracket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bracketId;
    
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    
    private int round;
    
    @OneToMany(mappedBy = "bracket", cascade = CascadeType.ALL)
    private List<Match> matches;
    
    // Getters and setters
    public Long getBracketId() {
        return bracketId;
    }
    
    public void setBracketId(Long bracketId) {
        this.bracketId = bracketId;
    }
    
    public Tournament getTournament() {
        return tournament;
    }
    
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public List<Match> getMatches() {
        return matches;
    }
    
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    
    // Business methods
    public void generateAutomatically() {
        // Logic to automatically generate tournament bracket
    }
    
    public void updateAfterMatch(Match match) {
        // Update bracket progression after a match
    }
}
