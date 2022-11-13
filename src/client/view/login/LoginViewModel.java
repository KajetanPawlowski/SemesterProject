package client.view.login;

import client.core.ViewModel;
import client.model.IClientModel;
import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel implements ViewModel {
    private IClientModel clientModel;

    private StringProperty userNameProperty = new SimpleStringProperty("");
    private StringProperty ipProperty = new SimpleStringProperty("");
    private StringProperty errorLabelProperty = new SimpleStringProperty("");


    public LoginViewModel(Model model){
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
    public boolean login(){
        return true;
    }

    public void logInError(){
        errorLabelProperty.setValue("Invalid Login Info");
    }

}
