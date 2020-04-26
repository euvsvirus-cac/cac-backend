package org.euvsvirus.cac.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Nils Knudsen
 * @since 20.04.20
 **/
@Entity
public class Skill implements Serializable {

    @Id
    private String id;

    private String name;

    private String teamId;

    public Skill() {
        // required default constructor
    }

    public Skill(String name, String teamId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.teamId = teamId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
