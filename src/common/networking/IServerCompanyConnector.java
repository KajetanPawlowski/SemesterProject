package common.networking;

import common.transferObjects.Company;

import java.rmi.RemoteException;

public interface IServerCompanyConnector extends IServerConnector{
    Company getCompanyProfile(String username)throws RemoteException;
    void createNewCompanyUser(Company newCompany)throws RemoteException;
}
