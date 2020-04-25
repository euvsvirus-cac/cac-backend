package org.euvsvirus.cac.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nils Knudsen
 * @since 25.04.20
 **/
@Embeddable
public class UserTeamKey implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "team_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTeamKey that = (UserTeamKey) o;
        return userId.equals(that.userId) &&
                teamId.equals(that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamId);
    }

}
