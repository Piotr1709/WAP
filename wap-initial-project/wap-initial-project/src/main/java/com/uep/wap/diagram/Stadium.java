package com.uep.wap.diagram;



import java.util.List;
import javax.persistence.*;

@Entity
public class Stadium {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String location;
    private int capacity;
    private String surface;
    private boolean isAvailable;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stadium_event",
            joinColumns = @JoinColumn(name = "stadium_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Match> matches;
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getSurface() {
        return surface;
    }
    
    public void setSurface(String surface) {
        this.surface = surface;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean isAvailable) {}; }
