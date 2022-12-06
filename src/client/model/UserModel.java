package client.model;

import common.networking.IServerConnector;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAdd;
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
    private UserModelState currentUserState;
    private IServerConnector server;
    ArrayList<String> allQualities = new ArrayList<>();



//    private static UserModel currentInstance = null;
//    private UserModel(){
//        currentInstance = this;
//        for(int i = 0; i <10 ; i++){
//            allQualities.add("Quality " + i);
//        }
//    }
//
//    public static UserModel getInstance(){
//        if(currentInstance == null){
//            return new UserModel();
//        }
//        return currentInstance;
//    }
//
//    public void setCurrentUserState(String username) throws UserNotFoundException{
//        char userType = checkUsertype(username);
//        try{
//            if(userType =='A'){
//                currentUserState = new ApplicantState(username);
//                log.quickClientLog("UserModel::setState::Applicant::" + currentUserState.getUsername());
//            }else{
//                currentUserState = new CompanyState(username);
//                log.quickClientLog("UserModel::setState::Company::" + currentUserState.getUsername());
//            }
//        }catch (RemoteException ex){
//            log.quickClientLog("UserModel::setCurrentUserState::RemoteException");
//            throw new UserNotFoundException();
//        }
//    }
//    private char checkUsertype(String username) throws UserNotFoundException{
//        char userType = 0;
//        try{
//            userType = server.getUsertype(username);
//        }catch (RemoteException ex){
//            log.quickClientLog("UserModel::checkUserType::RemoteException");
//        }
//        if(userType == 0){
//            throw new UserNotFoundException();
//        }
//        return userType;
//    }
//    public boolean connectToServer(String username, String ip) throws UserAlreadyConnectedException {
//        try {
//            String url = "rmi://" + ip + "/server";
//            server = (IServerConnector) Naming.lookup(url);
//            server.openConnection(username);
//            server.getUsertype(username)
//            return true;
//        } catch( RemoteException | MalformedURLException | NotBoundException ex ) {
//            return false;
//        }
//    }
//
//    @Override
//    public void disconnectFromServer(String username) {
//        try{
//            server.closeConnection(username);
//        }catch (RemoteException ex){
//            log.quickClientLog("UserModel::disconnectFormServer::RemoteException");
//        }
//
//    }
//
//    @Override
//    public void createNewUser(String username,char usertype) {
//        try {
//            if (usertype == 'A') {
//                currentUserState = new ApplicantState(new Applicant(username));
//                server.createNewApplicantUser((Applicant) currentUserState.getUserProfile());
//                log.quickClientLog("UserModel::setState::Applicant::" + currentUserState.getUsername());
//            } else {
//                currentUserState = new CompanyState(new Company(username));
//                server.createNewCompanyUser((Company) currentUserState.getUserProfile());
//                log.quickClientLog("UserModel::setState::Company::" + currentUserState.getUsername());
//            }
//        }catch (RemoteException ex){
//            log.quickClientLog("UserModel::createNewUser::RemoteException");
//        }
//    }
//
//    @Override
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

}
