package com.uep.wap.diagram;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;

@Entity
public class Tournament {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String startDate;
    private String endDate;
    private String format;
    private String type;
    private String location;
    private int maxPlayers;
    private String status;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Bracket bracket;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tournament_player",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tournament_team",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teams;
    
    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    private List<Match> matches;
    
    public Tournament() {
        // Default constructor required by JPA
    }
    
    public Tournament(String name, String startDate, String endDate,
                      String format, String type, String location) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.format = format;
        this.type = type;
        this.location = location;
        this.players = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }
    
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
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public int getMaxPlayers() {
        return maxPlayers;
    }
    
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Bracket getBracket() {
        return bracket;
    }
    
    public void setBracket(Bracket bracket) {
        this.bracket = bracket;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public List<Team> getTeams() {
        return teams;
    }
    
    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    public List<Match> getMatches() {
        return matches;
    }
    
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    
    // Business methods
    public void registerPlayer(Player player) {
        if (type.equals("singles")) {
            players.add(player);
        }
    }
    
    public void registerTeam(Team team) {
        if (type.equals("doubles")) {
            teams.add(team);
        }
    }
    
    public List<Match> getPlayerSchedule(Player player) {
        return matches.stream()
                .filter(match -> match.involvePlayer(player))
                .collect(Collectors.toList());
    }
    
    public boolean isSingles() {
        return "singles".equals(type);
    }
    
    public boolean isDoubles() {
        return "doubles".equals(type);
    }
    
    public void createDraws() {
        // Logic to create tournament draws
    }
    
    public void updateBrackets() {
        // Logic to update tournament brackets
    }
    
    public void generateSchedule() {
        // Logic to generate match schedules
    }
    
    public Long getTournamentId() {
        return id;
    }


}
