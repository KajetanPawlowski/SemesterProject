package client.view.editApplicantProfile;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EditApplicantProfileController implements FXMLController {
    private boolean isEditing = true;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {

    }

    @FXML
    private Button EditBtn;

    @FXML
    void onEditBtn(ActionEvent event) {
        if(isEditing){
            EditBtn.setText("Save");
            isEditing = false;
        }else{
            EditBtn.setText("Edit");
            isEditing = true;
        }
    }

}
