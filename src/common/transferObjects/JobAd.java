package common.transferObjects;


import java.util.ArrayList;

public class JobAd {
    private String jobTitle;
    private Company company;
    private String jobDescription;
    private ArrayList<Applicant> applicants;
    private ArrayList<String>requirements;

    public JobAd(String jobTitle, Company company, String jobDescription, ArrayList<String> requirements) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.jobDescription = jobDescription;
        this.requirements = requirements;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setApplicants(ArrayList<Applicant> applicants) {
        this.applicants = applicants;
    }

    public void setRequirements(ArrayList<String> requirements) {
        this.requirements = requirements;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Company getCompany() {
        return company;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public ArrayList<Applicant> getApplicants() {
        return applicants;
    }

    public ArrayList<String> getRequirements() {
        return requirements;
    }

    public void addApplicant(Applicant newApplicant){
        applicants.add(newApplicant);
    }
}
