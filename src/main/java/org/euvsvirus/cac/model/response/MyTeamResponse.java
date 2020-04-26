package org.euvsvirus.cac.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.euvsvirus.cac.model.Skill;
import org.euvsvirus.cac.model.Team;
import org.euvsvirus.cac.model.User;

import java.util.List;

@JsonIgnoreProperties({"teamId"})
public class MyTeamResponse {

    @JsonUnwrapped
    private Team team;

    private List<User> users;

    private List<Skill> skills;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
