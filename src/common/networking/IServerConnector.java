package common.networking;


import client.model.UserNotFoundException;
import common.transferObjects.JobAd;
import common.transferObjects.User;
import common.util.UserAlreadyConnectedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServerConnector extends Remote {
    void openConnection(String username)throws UserAlreadyConnectedException, RemoteException;
    void closeConnection(String username)throws RemoteException;
    void createNewUser(User user)throws RemoteException;
    void updateUser(User newUser) throws RemoteException;
    User getUser(String Username)throws RemoteException, UserNotFoundException;
    ArrayList<String> getAllQualities() throws RemoteException;
    void createJobAd(JobAd nextJobAd)throws RemoteException;
    void addNewQuality(String quality) throws RemoteException;
    // ArrayList<JobAdd> getRelevantJobAds(Applicant applicant, JobAdd relevantJobAd) throws RemoteException;
}
