package client.model;

import common.transferObjects.JobAdd;
import common.util.Subject;

import java.util.ArrayList;

public interface IUserModel extends Subject {
    void setCurrentUserState(String username)
            throws UserNotFoundException;
    String getUsername();
    UserModelState getUserState();
    ArrayList<JobAdd> getJobAdds();
    boolean connectToServer(String username, String ip);
    void disconnectFromServer(String username);
    void createNewUser(String username, char type);
    ArrayList<String> getAllQualities();
    void resetModel();
    void updateUserToServer();
}
