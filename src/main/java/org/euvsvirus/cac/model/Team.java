package org.euvsvirus.cac.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Nie ohne mein Team.
 *
 * @author Nils Knudsen
 * @since 23.04.20
 **/
@Entity
public class Team implements Serializable {

    @Id
    private String id;

    private String name;

    private String invitationId;

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

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }
}
