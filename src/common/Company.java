package common;

public class Company implements User{
    private final char TYPE = 'C';
    private String username;
    private String companyName;
    private String description;

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public char getType(){return TYPE;}
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDescription() {
        return description;
    }

    public Company(String username) {
        this.username = username;
    }
}
