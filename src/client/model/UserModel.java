package client.model;

import common.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class UserModel implements IUserModel {
    private LogBook log = LogBook.getInstance();
    private UserModelState currentUserState;
    private IServerConnector server;

    private static UserModel currentInstance = null;
    private UserModel(){

    }

    public static UserModel getInstance(){
        if(currentInstance == null){
            return new UserModel();
        }
        return currentInstance;
    }

    public void setCurrentUserState(String username) throws UserNotFoundException{
        char userType = 0;
        try{
            userType = server.getUsertype(username);
        }catch (RemoteException ex){
            log.quickClientLog("UserModel::setCurrentUserState::RemoteException");
        }
        if(userType == 0){
            throw new UserNotFoundException();
        }
        if(userType =='A'){
            currentUserState = new ApplicantState(username);
            log.quickClientLog("UserModel::setState::Applicant::" + currentUserState.getUsername());
        }else{
            currentUserState = new CompanyState(username);
            log.quickClientLog("UserModel::setState::Company::" + currentUserState.getUsername());
        }
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

    public String getUsername(){
        System.out.println("UserModel::getUserName");
        return currentUserState.getUsername();
    }

    public char getUsertype(){
        return currentUserState.getUserProfile().getType();
    }



    public ArrayList<JobAdd> getJobAdds(){
        return null;
    }

}
