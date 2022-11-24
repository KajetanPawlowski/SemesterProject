package client.model;

public interface IUserModel {
    void setCurrentUserState()
            throws UserNotFoundException;
}
