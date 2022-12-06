package server;

import common.networking.IServerConnector;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAdd;
import common.transferObjects.User;
import common.util.LogBook;
import common.util.UserAlreadyConnectedException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServerController implements IServerConnector {
    private ServerModel serverModel = new ServerModel(new JDBCConnector());
    LogBook log = LogBook.getInstance();
    public ServerController(ServerModel serverModel) throws RemoteException{
        this.serverModel = serverModel;
        UnicastRemoteObject.exportObject(this,0);
    }


    @Override
    public void openConnection(String username) throws RemoteException, UserAlreadyConnectedException {
        serverModel.openConnection(username);
        log.quickServerLog("ServerConnector::openConnection::"+username);
    }

    @Override
    public void closeConnection(String username) throws RemoteException {
        serverModel.closeConnection(username);
        log.quickServerLog("ServerConnector::closeConnection::"+username);
    }

    @Override
    public void createNewUser(String username, char type) throws RemoteException {

    }

    @Override
    public void updateUser(User newUser) throws RemoteException {
        serverModel.updateUser(newUser);
    }

    @Override
    public User getUser(String username) {
        return serverModel.getUser(username);
    }

    @Override
    public ArrayList<String> getAllQualities() throws RemoteException {
        return serverModel.getQualities();
    }

    @Override
    public void createJobAd(JobAdd nextJobAd) throws RemoteException {
        serverModel.createJobAd(nextJobAd);
    }


}
