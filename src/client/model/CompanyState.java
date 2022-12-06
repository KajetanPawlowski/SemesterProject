package client.model;

import common.networking.IServerCompanyConnector;
import common.networking.IServerConnector;
import common.transferObjects.Company;
import common.transferObjects.User;

public class CompanyState implements UserModelState{
    private Company company;
    private IServerCompanyConnector server;

    public CompanyState (Company company){
        this.company = company;
    }

    @Override
    public void init(String username) {

    }

    @Override
    public String getUsername() {
        return company.getUsername();
    }

    @Override
    public User getUserProfile() {
        return company;
    }

    @Override
    public IServerConnector getServerConnection() {
        return server;
    }

    @Override
    public void setServerConnection(IServerConnector serverConnection) {
        server = (IServerCompanyConnector) serverConnection;
    }

}
