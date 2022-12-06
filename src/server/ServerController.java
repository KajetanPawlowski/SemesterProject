package server;

import common.networking.IServerApplicantConnector;
import common.networking.IServerCompanyConnector;
import common.networking.IServerConnector;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAdd;
import common.transferObjects.User;
import common.util.LogBook;
import common.util.UserAlreadyConnectedException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServerController implements IServerApplicantConnector, IServerCompanyConnector {
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
    public char getUsertype(String username)throws RemoteException{
        char type = serverModel.getUserType(username);
        log.quickServerLog("ServerController::getUsertype::"+type);
        return type;
    }

    @Override
    public Applicant getApplicantProfile(String username) throws RemoteException {
        Applicant applicantProfile = serverModel.getApplicantProfile(username);
        return applicantProfile;
    }

    @Override
    public Company getCompanyProfile(String username) throws RemoteException {
        Company companyProfile = serverModel.getCompanyProfile(username);
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

    @Override
    public void updateUser(User newUser) throws RemoteException {
        serverModel.updateUser(newUser);
    }

    @Override
    public void createJobAd(JobAdd nextJobAd) throws RemoteException {
        serverModel.createJobAd(nextJobAd);
    }


}
