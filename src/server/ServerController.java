package server;

import client.model.UserNotFoundException;
import common.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServerController implements IServerConnector {
    private ServerModel serverModel = new ServerModel(new JDBCConnector());
    LogBook log = LogBook.getInstance();
    public ServerController(ServerModel serverModel) throws RemoteException{
        this.serverModel = serverModel;
        UnicastRemoteObject.exportObject(this,0);
    }


    @Override
    public char getUsertype(String username)throws RemoteException{
        char type = serverModel.getUserType(username);
        log.quickServerLog("ServerController::getUsertype::"+type);
        return type;
    }

    @Override
    public Applicant getApplicantProfile(Applicant user) throws RemoteException {
        Applicant applicantProfile = serverModel.getApplicantProfile(user);
        return applicantProfile;
    }

    @Override
    public Company getCompanyProfile(Company user) throws RemoteException {
        Company companyProfile = serverModel.getCompanyProfile(user);
        return companyProfile;
    }

    @Override
    public void createNewApplicantUser(Applicant newApplicant) throws RemoteException {
        serverModel.createNewApplicantUser(newApplicant);
    }

    @Override
    public void createNewCompanyUser(Company newCompany) throws RemoteException {
        serverModel.createNewCompanyUser(newCompany);
    }
}
