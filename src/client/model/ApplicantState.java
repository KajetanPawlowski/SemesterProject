package client.model;

import common.Applicant;
import common.User;

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
    @Override
    public User getUserProfile(){
        return applicant;
    }

    @Override
    public void setUserProfile(User user) {
        applicant = (Applicant) user;
    }
}
