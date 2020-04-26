package org.euvsvirus.cac.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.euvsvirus.cac.model.Skill;
import org.euvsvirus.cac.model.Team;
import org.euvsvirus.cac.model.User;

import java.util.Set;

@JsonIgnoreProperties({"teamId"})
public class MyTeamResponse {

    @JsonUnwrapped
    private Team team;

    private Set<User> users;

    private Set<Skill> skills;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }
}
