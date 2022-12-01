package client.model;

import common.Applicant;
import common.JobAdd;
import common.User;

import java.util.ArrayList;

public interface IUserModel {
    void setCurrentUserState(String username)
            throws UserNotFoundException;
    String getUsername();
    char getUsertype();
    ArrayList<JobAdd> getJobAdds();
}
