package common;

import client.model.UserNotFoundException;
import common.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerConnector extends Remote {
    char getUsertype(String username) throws RemoteException;
    // Applicant getApplicantProfile(Applicant user);
    // Company getCompanyProfile(Company user);
    // void createNewApplicantUser(Applicant newApplicant);
    // void createNewCompanyUser(Company newCompany);
}
