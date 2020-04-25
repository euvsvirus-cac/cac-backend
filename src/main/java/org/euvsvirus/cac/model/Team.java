package org.euvsvirus.cac.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;
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


    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<UserTeam> users;

    public Team() {
        this.id = UUID.randomUUID().toString();
    }

    public Set<UserTeam> getUsers() {
        return users;
    }

    public void setUsers(Set<UserTeam> users) {
        this.users = users;
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
