package client.model;

import common.transferObjects.JobAdd;
import common.transferObjects.User;
import common.util.Subject;
import common.util.UserAlreadyConnectedException;

import java.util.ArrayList;

public interface IUserModel extends Subject {
    boolean connectToServer(String username, String ip) throws UserNotFoundException, UserAlreadyConnectedException;
    void disconnectFromServer(String username);
    void updateUser();
    void createNewUser(String username, char type);
    User getUser();
    void resetModel();
    ArrayList<String> getAllQualities();



}
