package common;

import java.rmi.Remote;

public interface IServerConnector extends Remote {
    char getUserType();
}
