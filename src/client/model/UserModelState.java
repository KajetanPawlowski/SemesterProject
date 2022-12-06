package client.model;

import common.networking.IServerConnector;
import common.transferObjects.User;

public interface UserModelState {
    void init(String username);
    String getUsername();
    User getUserProfile();
    IServerConnector getServerConnection();
    void setServerConnection(IServerConnector serverConnection);


}
