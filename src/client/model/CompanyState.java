package client.model;

import common.Applicant;
import common.Company;

public class CompanyState implements UserModelState{
    private Company company;

    public CompanyState (String username){
        company = new Company(username);
    }

    @Override
    public void init(String username) {

    }

    @Override
    public String getUsername() {
        return company.getUsername();
    }
}
