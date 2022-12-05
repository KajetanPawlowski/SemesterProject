package client.view.User;

import client.core.FXMLController;
import client.core.ViewHandler;

import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class ProfileController implements FXMLController {
    private ViewHandler viewHandler;
    private ProfileViewModel profileVM;
    private static ProfileController instance = null;
        private Node list;
        @FXML
        private VBox rootVBox;

        @FXML
        private Label applicantName;

        @FXML
        private Label subtitle;

        @FXML
        private Circle profilePicture;

        @FXML
        private Label personalInformation;


    public void init(ViewHandler viewHandler, ViewModel vm){
        instance = this;
        this.viewHandler = viewHandler;
        this.profileVM = (ProfileViewModel) vm;
        //rootVBox.getChildren().add(QualitiesListView.createList("Qualities", userVM.getAllQualities()));
        applicantName.textProperty().bindBidirectional(profileVM.applicantNameProperty());
        personalInformation.textProperty().bindBidirectional(profileVM.personalInformationProperty());
        subtitle.textProperty().bindBidirectional(profileVM.subtitleProperty());
    }
    public static ProfileController getInstance(){
        return instance;
    }


}
