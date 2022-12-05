package client.model;

import common.Applicant;
import common.Company;
import common.User;

public class CompanyState implements UserModelState{
    private Company company;

    public CompanyState (Company company){
        this.company = company;
    }

    @Override
    public void init(String username) {

    }

    @Override
    public String getUsername() {
        return company.getUsername();
    }

    @Override
    public User getUserProfile() {
        return company;
    }

    @Override
    public void setUserProfile(User user) {
        company = (Company) user;
    }
}
