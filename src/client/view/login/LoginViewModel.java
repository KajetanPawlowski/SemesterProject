package client.view.login;

import client.core.ViewModel;
import client.model.IUserModel;
import client.model.UserModel;
import client.model.UserNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty userNameProperty = new SimpleStringProperty("");
    private StringProperty ipProperty = new SimpleStringProperty("");
    private StringProperty errorLabelProperty = new SimpleStringProperty("");


    public LoginViewModel(UserModel model){
        clientModel = model;
    }


    public StringProperty getUserNameProperty(){
        return userNameProperty;
    }
    public StringProperty getIpProperty(){
        return ipProperty;
    }
    public StringProperty getErrorLabelProperty(){
        return errorLabelProperty;
    }
    public void login() throws UserNotFoundException, InvalidLoginData{
        if(userNameProperty.get().equals("")){
            throw new InvalidLoginData();
        }
        if(ipProperty.get().equals("")){
            throw new InvalidLoginData();
        }
        clientModel.setCurrentUserState(userNameProperty.get());
    }

    public void logInError(){
        errorLabelProperty.setValue("Invalid Login Info");
    }

}
