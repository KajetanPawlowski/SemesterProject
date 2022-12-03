package server;

import client.model.UserNotFoundException;
import common.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


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
    public void createJobAd(JobAdd nextJobAd) throws RemoteException {
        serverModel.createJobAd(nextJobAd);
    }

    @Override
    public ArrayList<JobAdd> getRelevantJobAds(Applicant applicant, JobAdd relevantJobAd) throws RemoteException {
        ArrayList<JobAdd> relevantJobAds = serverModel.getRelevantJobAds(applicant, relevantJobAd);
        return relevantJobAds;
    }
}
