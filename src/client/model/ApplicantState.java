package client.model;

import common.Applicant;

public class ApplicantState implements UserModelState{
    private Applicant applicant;

    public ApplicantState (String username){
        applicant = new Applicant(username);
    }

    @Override
    public void init(String username) {

    }

    @Override
    public String getUsername() {
        return applicant.getUsername();
    }
}
