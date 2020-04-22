package org.euvsvirus.cac.model.request;

/**
 * TODO: Ändere mich in eine Beschreibung
 *
 * @author Nils Knudsen
 * @since 22.04.20
 **/
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
