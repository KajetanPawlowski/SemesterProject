package client.model;

import common.Applicant;
import common.Company;
import common.JobAdd;
import common.User;

import java.util.ArrayList;

public class UserModel implements IUserModel {
    private UserModelState currentUserState;

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
        char userType = 'C'; //server.getUsertype(username) // 0 for empty
        if(userType == 0){
            throw new UserNotFoundException();
        }
        if(userType =='A'){
            currentUserState = new ApplicantState(username);
            System.out.println("UserModel::setState::Applicant::" + currentUserState.getUsername());
        }else{
            currentUserState = new CompanyState(username);
            System.out.println("UserModel::setState::Company::" + currentUserState.getUsername());
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
