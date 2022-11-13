package client.view.applicant;

import client.core.FXMLController;
import client.core.ViewHandler;

import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ApplicantController implements FXMLController {
    private ViewHandler viewHandler;
    private ApplicantViewModel applicantVM;




    public void init(ViewHandler viewHandler, ViewModel vm){
        this.viewHandler = viewHandler;
        this.applicantVM = (ApplicantViewModel) vm;
    }

}
