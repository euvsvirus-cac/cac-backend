package org.euvsvirus.cac.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * @author Nils Knudsen
 * @since 20.04.20
 **/
@Entity
public class User implements UserDetails {

    @Id
    private String id;

    private String fullName;

    private String displayName;

    private String email;

    private String password;

    // TODO: Load skills lazily in a transactional service?
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserSkill> skills;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserTeam> teams;

    public User() {
            this.id = UUID.randomUUID().toString();
    }

    public Set<UserTeam> getTeams() {
        return teams;
    }

    public void setTeams(Set<UserTeam> teams) {
        this.teams = teams;
    }

    public String getId() {
        return id;
    }

    public void setId(String uid) {
        this.id = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<UserSkill> skills) {
        this.skills = skills;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

}
