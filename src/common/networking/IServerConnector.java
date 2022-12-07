package common.networking;

import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAdd;
import common.transferObjects.User;
import common.util.UserAlreadyConnectedException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServerConnector extends Remote {
    void openConnection(String username)throws UserAlreadyConnectedException, RemoteException;
    void closeConnection(String username)throws RemoteException;
    void createNewUser(String username, char type)throws RemoteException;
    void updateUser(User newUser) throws RemoteException;
    User getUser(String Username)throws RemoteException;
    ArrayList<String> getAllQualities(String username) throws RemoteException;
    void createJobAd(JobAdd nextJobAd)throws RemoteException;

    ArrayList<String> getAllQualities() throws RemoteException;
    // ArrayList<JobAdd> getRelevantJobAds(Applicant applicant, JobAdd relevantJobAd) throws RemoteException;
}
