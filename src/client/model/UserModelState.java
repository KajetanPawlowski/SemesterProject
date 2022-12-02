package client.model;

import common.User;

public interface UserModelState {
    void init(String username);
    String getUsername();
    User getUserProfile();
    void setUserProfile(User user);

}
