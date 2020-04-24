package org.euvsvirus.cac.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Nie ohne mein Team.
 *
 * @author Nils Knudsen
 * @since 23.04.20
 **/
@Entity
public class Team {

    @Id
    private String id;

    private String name;

    public Team() {
        this.id = UUID.randomUUID().toString();
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
}
