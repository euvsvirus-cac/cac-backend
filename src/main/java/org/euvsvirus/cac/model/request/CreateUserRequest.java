package org.euvsvirus.cac.model.request;

/**
 * @author Nils Knudsen
 * @since 22.04.20
 **/
public class CreateUserRequest {

    private String invitationCode;

    private String email;

    private String fullName;

    private String password;

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
