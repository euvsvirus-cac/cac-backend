package org.euvsvirus.cac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Nils Knudsen
 * @since 25.04.20
 **/

@Entity
public class TeamUser implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private UserTeamKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"users", "teams"})
    private User user;

    @ManyToOne
    @MapsId("team_id")
    @JoinColumn(name = "team_id")
    @JsonIgnoreProperties({"users", "teams"})
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
