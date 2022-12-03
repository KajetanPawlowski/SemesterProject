package common;

import client.model.UserNotFoundException;
import common.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServerConnector extends Remote {
    char getUsertype(String username) throws RemoteException;
    Applicant getApplicantProfile(String username)throws RemoteException;
    Company getCompanyProfile(String username)throws RemoteException;
    void createNewApplicantUser(Applicant newApplicant)throws RemoteException;
    void createNewCompanyUser(Company newCompany)throws RemoteException;
    void createJobAd(JobAdd nextJobAd)throws RemoteException;
    ArrayList<JobAdd> getRelevantJobAds(Applicant applicant, JobAdd relevantJobAd) throws RemoteException;
}
