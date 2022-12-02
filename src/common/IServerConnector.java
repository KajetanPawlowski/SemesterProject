package common;

import client.model.UserNotFoundException;
import common.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerConnector extends Remote {
    char getUsertype(String username) throws RemoteException;
    Applicant getApplicantProfile(Applicant user)throws RemoteException;
    Company getCompanyProfile(Company user)throws RemoteException;
    void createNewApplicantUser(Applicant newApplicant)throws RemoteException;
    void createNewCompanyUser(Company newCompany)throws RemoteException;
}
