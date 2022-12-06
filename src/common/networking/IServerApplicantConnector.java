package common.networking;

import common.transferObjects.Applicant;

import java.rmi.RemoteException;

public interface IServerApplicantConnector extends IServerConnector{
    Applicant getApplicantProfile(String username)throws RemoteException;
    void createNewApplicantUser(Applicant newApplicant)throws RemoteException;
}
