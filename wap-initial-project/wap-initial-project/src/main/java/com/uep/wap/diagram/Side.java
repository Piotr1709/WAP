package com.uep.wap.diagram;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sides")
public class Side {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sideId;
    
    @Column(nullable = false)
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    
    public Side() {
    }
    
    public Side(String type) {
        this.type = type;
    }
    
    public Integer getSideId() {
        return sideId;
    }
    
    public void setSideId(Integer sideId) {
        this.sideId = sideId;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Team getTeam() {
        return team;
    }
    
    public void setTeam(Team team) {
        this.team = team;
    }
    
    public boolean isTeam() {
        return "team".equals(type);
    }
    
    public boolean isSinglePlayer() {
        return "single".equals(type);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Side side = (Side) o;
        return Objects.equals(sideId, side.sideId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sideId);
    }
    
    @Override
    public String toString() {
        return "Side{" +
                "sideId=" + sideId +
                ", type='" + type + '\'' +
                '}';
    }
}
