package client.model;

import common.JobAdd;

import java.util.ArrayList;

public interface IUserModel {
    void setCurrentUserState(String username)
            throws UserNotFoundException;
    String getUsername();
    void setJobAdds(int noOfJobs);
    ArrayList<JobAdd> getJobAdds();
}
