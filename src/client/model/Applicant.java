package client.model;

public class Applicant {
    private String username;
    private String fullName;
    private String profileDescription;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public Applicant(String username) {
        this.username = username;
    }
}
