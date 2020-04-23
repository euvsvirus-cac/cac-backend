package org.euvsvirus.cac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"user"})
public class UserSkill implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private UserSkillKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("skill_id")
    @JoinColumn(name = "skill_id")
    @JsonUnwrapped
    private Skill skill;

    @Enumerated(EnumType.STRING)
    private Level level;

    public UserSkillKey getId() {
        return id;
    }

    public void setId(UserSkillKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
