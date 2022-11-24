package client.model;

public interface IUserModel {
    void setCurrentUserState(String username)
            throws UserNotFoundException;
    String getUsername();
}
