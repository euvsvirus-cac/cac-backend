package org.euvsvirus.cac.model.request;

/**
 * @author Nils Knudsen
 * @since 24.04.20
 **/
public class UserTeamRequest {

    private String userId;

    private String teamId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
