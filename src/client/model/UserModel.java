package client.model;

import common.networking.IServerConnector;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAdd;
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
        for(int i = 0; i <10 ; i++){
            allQualities.add("Quality " + i);
        }
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
            try {
                server.closeConnection(clientUser.getUsername());
                updateUser();
            }catch (RemoteException ex){
                log.quickClientLog("UserModel::resetModel::RemoteException");
            }
        }
        currentInstance = null;
    }

    @Override
    public ArrayList<String> getAllQualities() {
        try {
            return server.getAllQualities();
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::getAlQualities::RemoteException");
        }
        return null;
    }


    //    public ArrayList<String> getAllQualities() {
//        return allQualities;
//    }
//
//    @Override
//    public void resetModel() {
//        try{
//            server.closeConnection(currentInstance.getUsername());
//        }catch(RemoteException ex){
//            log.quickClientLog("UserModel::resetModel::RemoteException");
//        }
//
//        currentInstance = new UserModel();
//    }
//
//    @Override
//    public void updateUserToServer() {
//        try{
//            server.updateUser(currentUserState.getUserProfile());
//            log.quickClientLog("UserModel::UpdateUserToServer");
//        }catch (RemoteException ex){
//            log.quickClientLog("UserModel::UpdateUserToServer::RemoteException");
//        }
//    }
//
//
//    public String getUsername(){
//        System.out.println("UserModel::getUserName");
//        return currentUserState.getUsername();
//    }
//
//    public UserModelState getUserState(){
//        return currentUserState;
//    }
//
//
//
//    public ArrayList<JobAdd> getJobAdds(){
//        return null;
//    }
//
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
            server.updateUser(clientUser);
            log.quickClientLog("UserModel::updateUser::"+clientUser.getUsername());
        } catch( RemoteException ex ) {
            log.quickClientLog("UserModel::updateUser::RemoteException");
        }
    }

    @Override
    public void createNewUser(String username, char type) {
        try {
            server.createNewUser(username,type);
            if(type == 'A'){
                clientUser = new Applicant(username);
            }else{
                clientUser = new Company(username);
            }
            log.quickClientLog("UserModel::createNewUser::"+username);
        } catch( RemoteException ex ) {
            log.quickClientLog("UserModel::createNewUser::RemoteException");
        }
    }

    @Override
    public User getUser() {
        return clientUser;
    }
}
