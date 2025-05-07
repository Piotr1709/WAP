package com.uep.wap.diagram;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "referees")
public class Referee extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refereeId;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @OneToMany(mappedBy = "referee", cascade = CascadeType.ALL)
    private List<Match> matches = new ArrayList<>();
    
    public Referee() {
        // Default constructor required by JPA
    }
    
    public Referee(String username, String email, String password,
                   String firstName, String lastName) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Long getRefereeId() {
        return refereeId;
    }
    
    public void setRefereeId(Long refereeId) {
        this.refereeId = refereeId;
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
    
    public List<Match> getMatches() {
        return matches;
    }
    
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    
    public List<Match> viewAssignedMatches(Tournament tournament) {
        // Implementation to filter matches by tournament
        return matches.stream()
                .filter(match -> match.getTournament().equals(tournament))
                .toList();
    }
    
    public void enterMatchComments(Match match, String comments) {
        // Implementation to add comments to a specific match
        if (matches.contains(match)) {
            match.setComments(comments);
        }
    }
    
    public void addMatch(Match match) {
        matches.add(match);
        match.setReferee(this);
    }
    
    public void removeMatch(Match match) {
        matches.remove(match);
        match.setReferee(null);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Referee referee = (Referee) o;
        return Objects.equals(refereeId, referee.refereeId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), refereeId);
    }
    
    @Override
    public String toString() {
        return "Referee{" +
                "refereeId=" + refereeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
