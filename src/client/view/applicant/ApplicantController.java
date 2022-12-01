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
    private static ApplicantController instance = null;

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
        instance = this;
        this.viewHandler = viewHandler;
        this.applicantVM = (ApplicantViewModel) vm;
    }
    public static ApplicantController getInstance(){
        return instance;
    }

    public void allowEditing(){
        PersonalInformation.setEditable(true);
        Education.setEditable(true);
        Experience.setEditable(true);
        ContactInfo.setEditable(true);
        Languages.setEditable(true);
        Skills.setEditable(true);
    }
    public void lockEditing(){
        PersonalInformation.setEditable(false);
        Education.setEditable(false);
        Experience.setEditable(false);
        ContactInfo.setEditable(false);
        Languages.setEditable(false);
        Skills.setEditable(false);
    }



}
