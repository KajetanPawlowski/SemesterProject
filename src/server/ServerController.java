package server;

import client.model.UserNotFoundException;
import common.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerController implements IServerConnector {
    LogBook log = LogBook.getInstance();
    public ServerController() throws RemoteException{
        UnicastRemoteObject.exportObject(this,0);
    }
    @Override
    public char getUsertype(String username)throws RemoteException{
        char type = 0;
        log.quickServerLog("ServerController::getUsertype::"+type);
        return type;
    }
}
