package client.view.login;

import client.core.ViewModel;
import client.model.IUserModel;
import client.model.UserNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty userNameProperty = new SimpleStringProperty("");
    private StringProperty ipProperty = new SimpleStringProperty("10.154.220.106");
    private StringProperty errorLabelProperty = new SimpleStringProperty("");


    public LoginViewModel(IUserModel model){
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
            throw new InvalidLoginData("Invalid Username");
        }
        if(ipProperty.get().equals("")){
            throw new InvalidLoginData("Invalid IP");
        }
        //IMPORTANT ORDER
        if(clientModel.connectToServer(ipProperty.get())){
            System.out.println("LoginView::login::success");
            clientModel.setCurrentUserState(userNameProperty.get());
        }else{
            System.out.println("LoginView::login::fail");
            throw new InvalidLoginData("Server not found");
        }


    }

    public void logInError(InvalidLoginData exception){
        errorLabelProperty.setValue(exception.getMsg());
    }

    public void createNewUser(char type){
        clientModel.createNewUser(userNameProperty.get(),type);
    }

}
