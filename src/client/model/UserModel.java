package client.model;

import common.*;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserModel implements IUserModel {
    private LogBook log = LogBook.getInstance();
    private UserModelState currentUserState;
    private IServerConnector server;
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

    public void setCurrentUserState(String username) throws UserNotFoundException{
        char userType = checkUsertype(username);
        try{
            if(userType =='A'){
                currentUserState = new ApplicantState(server.getApplicantProfile(username));
                log.quickClientLog("UserModel::setState::Applicant::" + currentUserState.getUsername());
            }else{
                currentUserState = new CompanyState(server.getCompanyProfile(username));
                log.quickClientLog("UserModel::setState::Company::" + currentUserState.getUsername());
            }
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::setCurrentUserState::RemoteException");
            throw new UserNotFoundException();
        }
    }
    private char checkUsertype(String username) throws UserNotFoundException{
        char userType = 0;
        try{
            userType = server.getUsertype(username);
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::checkUserType::RemoteException");
        }
        if(userType == 0){
            throw new UserNotFoundException();
        }
        return userType;
    }
    public boolean connectToServer(String ip){
        try {
            String url = "rmi://" + ip + "/server";
            server = (IServerConnector) Naming.lookup( url );
            return true;
        } catch( Exception ex ) {
            return false;
        }
    }

    @Override
    public void createNewUser(String username,char usertype) {
        try {
            if (usertype == 'A') {
                currentUserState = new ApplicantState(new Applicant(username));
                server.createNewApplicantUser((Applicant) currentUserState.getUserProfile());
                log.quickClientLog("UserModel::setState::Applicant::" + currentUserState.getUsername());
            } else {
                currentUserState = new CompanyState(new Company(username));
                server.createNewCompanyUser((Company) currentUserState.getUserProfile());
                log.quickClientLog("UserModel::setState::Company::" + currentUserState.getUsername());
            }
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::createNewUser::RemoteException");
        }
    }

    @Override
    public ArrayList<String> getAllQualities() {
        return allQualities;
    }


    public String getUsername(){
        System.out.println("UserModel::getUserName");
        return currentUserState.getUsername();
    }

    public UserModelState getUserState(){
        return currentUserState;
    }



    public ArrayList<JobAdd> getJobAdds(){
        return null;
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

}
