package client.model;

import common.networking.IServerConnector;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAd;
import common.transferObjects.User;
import common.util.LogBook;
import common.util.Observer;
import common.util.UserAlreadyConnectedException;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserModel implements IUserModel {
    private LogBook log = LogBook.getInstance();
    private IServerConnector server;
    private User clientUser;
    ArrayList<String> allQualities = new ArrayList<>();


    private static UserModel currentInstance = null;
    private UserModel(){
        currentInstance = this;
    }

    public static UserModel getInstance(){
        if(currentInstance == null){
            return new UserModel();
        }
        return currentInstance;
    }
    @Override
    public void resetModel() {
        if(clientUser != null){
            disconnectFromServer(clientUser.getUsername());
        }
        currentInstance = null;
    }

    @Override
    public ArrayList<String> getAllQualities() {
        try {
          return server.getAllQualities();
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::getAlQualities::RemoteException");}
        return null;
    }

    @Override
    public void addNewQuality(String quality){
        try {
            server.addNewQuality(quality);
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::addNewQuality::RemoteException");
        }
    }

    @Override
    public void applyForJob(JobAd jobAd) {
        jobAd.addApplicant((Applicant)clientUser);
        System.out.println("applyForJob" + jobAd.toString());
        try {
            server.updateJobAd(jobAd);
        } catch (RemoteException e) {
            log.quickClientLog("UserModel::applyForJob::RemoteException");
        }
    }

    private ArrayList<Observer> observers = new ArrayList<Observer>();

    @Override
    public synchronized void attachObserver( Observer observer )
    {
        observers.add( observer );
    }
    @Override
    public synchronized void detachObserver( Observer observer)
    {
        observers.remove( observer );
    }
    @Override
    public void notifyObservers()
    {
        System.out.println("ClientModel::notifyAllObservers");
        for( Observer o: observers )
            o.update();
    }

    @Override
    public boolean connectToServer(String username, String ip) throws UserNotFoundException, UserAlreadyConnectedException {
        try {
            String url = "rmi://" + ip + "/server";
            server = (IServerConnector) Naming.lookup(url);
            server.openConnection(username);
            clientUser = server.getUser(username);
            return true;
        } catch( RemoteException | MalformedURLException | NotBoundException ex ) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void disconnectFromServer(String username) {
        try {
            server.closeConnection(username);
        } catch( RemoteException ex ) {
            log.quickClientLog("UserModel::disconnectFormServer::RemoteException");
        }
    }

    @Override
    public void updateUser() {
        try {
            log.quickClientLog("UserModel::updateUser::"+clientUser.getUsername());
            server.updateUser(clientUser);
        } catch( RemoteException ex ) {
            log.quickClientLog("UserModel::updateUser::RemoteException");
        }
    }

    @Override
    public void createNewUser(String username, char type) {
        try {
            if(type == 'A'){
                clientUser = new Applicant(username);
            }else{
                clientUser = new Company(username);
            }
            log.quickClientLog("UserModel::createNewUser::"+username);
            server.createNewUser(clientUser);
        } catch( RemoteException ex ) {
            log.quickClientLog("UserModel::createNewUser::RemoteException");
        }
    }

    @Override
    public void createNewJobAd(JobAd nextJobAd) {
        try {
            server.addNewJobAd(nextJobAd);
        } catch( RemoteException ex ) {
            ex.printStackTrace();
            log.quickClientLog("UserModel::createNewJobAd::RemoteException");
        }

    }

    @Override
    public void updateJobAd(JobAd nextJobAd) {
        try {
            server.updateJobAd(nextJobAd);
        } catch (RemoteException e) {
            log.quickClientLog("UserModel::updateJobAd::RemoteException");
        }
    }

    @Override
    public User getUser() {
        return clientUser;
    }

    @Override
    public ArrayList<JobAd> getClientJobAds() {
        try{
            return server.getRelevantJobAds(clientUser);
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::getClientJobAds::RemoteException");
        }
        return null;
    }
}
