package client.view.editUserProfile;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import client.view.User.QualitiesListView;
import client.view.User.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class EditUserProfileController implements FXMLController {
    private boolean isEditing = true;
    private ViewHandler viewHandler;
    private EditUserProfileViewModel editApplicantProfileVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        editApplicantProfileVM = (EditUserProfileViewModel) vm;
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
            UserController.getInstance().allowEditing();
            QualitiesListView.getInstance().setEditable(false);


        }else{
            EditBtn.setText("Edit");
            isEditing = true;
            UserController.getInstance().lockEditing();
            QualitiesListView.getInstance().setEditable(true);
            //ViewModel.saveChanges();
        }
    }

}
