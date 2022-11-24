package client.view.login;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.model.UserNotFoundException;
import client.view.menu.MenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginViewController implements FXMLController {
    private ViewHandler viewHandler;
    private LoginViewModel logInVM;
    private MenuController menuControl;
    @FXML
    TextField loginTextField;
    @FXML
    TextField ipTextField;
    @FXML
    Label errorLabel;


    public void init(ViewHandler viewHandler, ViewModel vm){
        this.viewHandler = viewHandler;
        this.logInVM = (LoginViewModel) vm;
        menuControl = new MenuController();
        loginTextField.textProperty().bindBidirectional(logInVM.getUserNameProperty());
        ipTextField.textProperty().bindBidirectional(logInVM.getIpProperty());
        errorLabel.textProperty().bindBidirectional(logInVM.getErrorLabelProperty());
    }


    @FXML
    public void onLoginBtn(){
        try {
            logInVM.login();
            viewHandler.openOverview();
        }catch (UserNotFoundException userNotFound){
            System.out.println("LoginViewController::onLoginBtn::UserNotFoundException");
            //viewHandler.openCreateProfileView();
        }catch (InvalidLoginData invalidData){
            System.out.println("LoginViewController::onLoginBtn::InvalidLoginDataException");
            logInVM.logInError();
        }
    }
}
