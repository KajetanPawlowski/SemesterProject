package client.view.applicant;

import client.core.FXMLController;
import client.core.ViewHandler;

import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;



import java.util.ArrayList;

public class ApplicantController implements FXMLController {
    private ViewHandler viewHandler;
    private ApplicantViewModel applicantVM;

        @FXML
        private Label ApplicantName;

        @FXML
        private Label Title;

        @FXML
        private Circle ProfilePicture;

        @FXML
        private TextArea PersonalInformation;

        @FXML
        private TextArea Education;

        @FXML
        private TextArea Experience;

        @FXML
        private TextArea ContactInfo;

        @FXML
        private TextArea Languages;

        @FXML
        private TextArea Skills;


    public void init(ViewHandler viewHandler, ViewModel vm){
        this.viewHandler = viewHandler;
        this.applicantVM = (ApplicantViewModel) vm;
    }

    public void allowEditing(){
        Languages.setEditable(true);
    }
    public void lockEditing(){
        Languages.setEditable(false);
    }

}
