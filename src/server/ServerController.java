package server;

import common.networking.IServerConnector;
import common.transferObjects.Applicant;
import common.transferObjects.JobAd;
import common.transferObjects.User;
import common.util.LogBook;
import common.util.UserAlreadyConnectedException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServerController implements IServerConnector {
    private ServerModel serverModel;
    LogBook log = LogBook.getInstance();
    public ServerController(ServerModel serverModel) throws RemoteException{
        this.serverModel = serverModel;
        UnicastRemoteObject.exportObject(this,0);
    }


    @Override
    public void openConnection(String username) throws RemoteException, UserAlreadyConnectedException {
        serverModel.openConnection(username);
    }

    @Override
    public void closeConnection(String username) throws RemoteException {
        serverModel.closeConnection(username);
    }

    @Override
    public void createNewUser(User user) throws RemoteException {
        serverModel.createNewUser(user);
    }

    @Override
    public void updateUser(User newUser) throws RemoteException {
        serverModel.updateUser(newUser);
    }

    @Override
    public void updateJobAd(JobAd ad) throws RemoteException {
        serverModel.updateJobAd(ad);
    }

    @Override
    public User getUser(String username) {
        return serverModel.getUser(username);
    }

    @Override
    public ArrayList<String> getAllQualities() throws RemoteException{
        return serverModel.getQualities();
    }

    @Override
    public void createJobAd(JobAd nextJobAd) throws RemoteException {
        serverModel.createJobAd(nextJobAd);
    }

    @Override
    public void addNewQuality(String quality) throws RemoteException {
        serverModel.insertNewQuality(quality);
    }

    @Override
    public void addNewJobAd(JobAd nextAd) throws RemoteException {
        serverModel.createJobAd(nextAd);
    }

    @Override
    public ArrayList<JobAd> getRelevantJobAds(User user) throws RemoteException {
        return serverModel.getRelevantJobAds(user);
    }

    @Override
    public ArrayList<Applicant> getJobAdApplicants(User user) throws RemoteException{
        return serverModel.getJobAdApplicants(user);
    }


}
