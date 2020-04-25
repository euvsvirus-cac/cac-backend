package org.euvsvirus.cac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Nils Knudsen
 * @since 25.04.20
 **/

@Entity
@JsonIgnoreProperties({"user"})
public class UserTeam implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private UserTeamKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("team_id")
    @JoinColumn(name = "team_id")
    @JsonUnwrapped
    private Team team;

    public UserTeamKey getId() {
        return id;
    }

    public void setId(UserTeamKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
