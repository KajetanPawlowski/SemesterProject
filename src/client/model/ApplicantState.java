package client.model;

import common.networking.IServerApplicantConnector;
import common.networking.IServerConnector;
import common.transferObjects.Applicant;
import common.transferObjects.User;

public class ApplicantState implements UserModelState{
    private Applicant applicant;
    private IServerApplicantConnector server;

    public ApplicantState (Applicant applicant){
        this.applicant = applicant;
    }

    @Override
    public void init(String username) {

    }

    @Override
    public String getUsername() {
        return applicant.getUsername();
    }
    @Override
    public User getUserProfile(){
        return applicant;
    }

    @Override
    public IServerConnector getServerConnection() {
        return server;
    }

    @Override
    public void setServerConnection(IServerConnector serverConnection) {
        server = (IServerApplicantConnector) serverConnection;
    }

}
