package common.transferObjects;


import java.io.Serializable;
import java.util.ArrayList;

public class JobAd implements Serializable {
    private int jobId;
    private String jobTitle;
    private User company;
    private String jobDescription;
    private ArrayList<Applicant> applicants;
    private ArrayList<String>requirements;


    public JobAd(String jobTitle, User company, String jobDescription, ArrayList<String> requirements) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.jobDescription = jobDescription;
        this.requirements = requirements;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public JobAd(int id, String jobTitle, User company, String jobDescription, ArrayList<String> requirements) {
        jobId = id;
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

    public User getCompany() {
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

    public String getRequirementsForDB(){
        String result = "[";
        for(int i = 0; i < requirements.size(); i++){
            result += "'" + requirements.get(i) + "'";
            if(i+1 < requirements.size()){
                result += ", ";
            }
        }
        result += "]";
        return result;
    }

    public String toString(){
        return jobTitle + "::" + company.getFullName() + "::" + jobDescription;
    }

    public void addApplicant(Applicant newApplicant){
        applicants.add(newApplicant);
    }
}
