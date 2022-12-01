package client.view.editApplicantProfile;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.view.applicant.ApplicantController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditApplicantProfileController implements FXMLController {
    private boolean isEditing = true;
    private ViewHandler viewHandler;
    private EditApplicantProfileViewModel editApplicantProfileVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        editApplicantProfileVM = (EditApplicantProfileViewModel) vm;
    }

    @FXML
    private Button EditBtn;

    @FXML
    private BorderPane borderRoot;

    @FXML
    void onEditBtn(ActionEvent event) {
        if(isEditing){
            EditBtn.setText("Save");
            isEditing = false;
            ApplicantController.getInstance().allowEditing();


        }else{
            EditBtn.setText("Edit");
            isEditing = true;
            ApplicantController.getInstance().lockEditing();
            //ViewModel.saveChanges();
        }
    }

}
