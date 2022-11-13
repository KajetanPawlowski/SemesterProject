package client.model;


import java.util.ArrayList;

public class JobAdd {
    private String jobTitle;
    private Company company;
    private String subtitle;
    private ArrayList<Applicant> applicants;
    private String jobDescription;
    private String Requirements;

    public JobAdd(String jobTitle, Company company, String subtitle, String jobDescription, String requirements) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.subtitle = subtitle;
        this.jobDescription = jobDescription;
        Requirements = requirements;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Company getCompany() {
        return company;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getRequirements() {
        return Requirements;
    }

    public void addApplicant(Applicant newApplicant){
        applicants.add(newApplicant);
    }
}
