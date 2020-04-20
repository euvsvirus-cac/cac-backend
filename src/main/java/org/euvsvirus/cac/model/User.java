package org.euvsvirus.cac.model;

import java.util.Map;

/**
 * @author Nils Knudsen
 * @since 20.04.20
 **/
public class User {

    private String uid;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Map<Skill, Level> skills;

}
