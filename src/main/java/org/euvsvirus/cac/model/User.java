package org.euvsvirus.cac.model;

import java.util.Map;

/**
 *
 * @author Nils Knudsen
 * @since 20.04.20
 **/
public class User {

    private String firstName;

    private String lastName;

    private String eMailAddress;

    private String password;

    private Map<Skill, Level> userSkills;

}
