package common;

public class Applicant implements User{
    final char TYPE = 'A';
    private String username;
    private String fullName;
    private String personalInformation;
    private String contact;
    private String education;
    private String languages;
    private String experience;
    private String skills;

    public String getUsername() {return username;}
    public char getType(){return TYPE;}
    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getPersonalInformation() {return personalInformation;}

    public void setProfileInformation(String profileInformation) {this.personalInformation = profileInformation;}

    public String getContact() {return contact;}

    public void setContact(String contact) {this.contact = contact;}

    public String getEducation() {return education;}

    public void setEducation(String education) {this.education = education;}

    public String getLanguages() {return languages;}

    public void setLanguages(String languages) {this.languages = languages;}

    public String getExperience() {return experience;}

    public void setExperience(String experience) {this.experience = experience;}

    public String getSkills() {return skills;}

    public void setSkills(String skills) {this.skills = skills;}

    public Applicant(String username) {
        this.username = username;
    }
}
