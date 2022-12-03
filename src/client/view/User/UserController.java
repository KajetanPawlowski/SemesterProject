package client.view.User;

import client.core.FXMLController;
import client.core.ViewHandler;

import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class UserController implements FXMLController {
    private ViewHandler viewHandler;
    private UserViewModel userVM;
    private static UserController instance = null;
        @FXML
        private VBox rootVBox;

        @FXML
        private TextField applicantName;

        @FXML
        private TextField subtitle;

        @FXML
        private Circle ProfilePicture;

        @FXML
        private TextArea PersonalInformation;


    public void init(ViewHandler viewHandler, ViewModel vm){
        instance = this;
        this.viewHandler = viewHandler;
        this.userVM = (UserViewModel) vm;
        rootVBox.getChildren().add(QualitiesListView.createList("Qualities", userVM.getQualities()));
    }
    public static UserController getInstance(){
        return instance;
    }

    public void allowEditing(){
        applicantName.setEditable(true);
        subtitle.setEditable(true);
        PersonalInformation.setEditable(true);

    }
    public void lockEditing(){
        applicantName.setEditable(false);
        subtitle.setEditable(false);
        PersonalInformation.setEditable(false);

    }



}
