package client.view.login;

import client.core.ViewModel;
import client.model.IUserModel;
import client.model.UserNotFoundException;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty userNameProperty = new SimpleStringProperty("");
    private StringProperty ipProperty = new SimpleStringProperty("10.154.222.101");
    private StringProperty errorLabelProperty = new SimpleStringProperty("");


    public LoginViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
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

    public void logInError(String msg){
        errorLabelProperty.setValue(msg);
    }

    public void createNewUser(char type){
        clientModel.createNewUser(userNameProperty.get(),type);
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //UPDATE CODE
            }
        });
    }
}
