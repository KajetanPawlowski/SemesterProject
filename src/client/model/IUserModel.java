package client.model;

import common.Applicant;
import common.JobAdd;
import common.Subject;
import common.User;

import java.util.ArrayList;

public interface IUserModel extends Subject {
    void setCurrentUserState(String username)
            throws UserNotFoundException;
    String getUsername();
    UserModelState getUserState();
    ArrayList<JobAdd> getJobAdds();
    boolean connectToServer(String ip);
    void createNewUser(String username, char type);
    ArrayList<String> getAllQualities();
}
