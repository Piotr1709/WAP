package com.uep.wap.diagram;

public class Referee extends User {
    private int refereeId;
    private String firstName;
    private String lastName;

    public Referee(int userId, String username, String email, String password,
                   int refereeId, String firstName, String lastName) {
        super(userId, username, email, password);
        this.refereeId = refereeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Match> viewAssignedMatches(Tournament tournament) {
        // Return matches assigned to this referee
        return null;
    }

    public void enterMatchComments(Match match, String comments) {
        // Add comments to a specific match
    }
}
