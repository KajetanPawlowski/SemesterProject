package client.view.editUserProfile;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class EditUserProfileController implements FXMLController {
    private boolean isEditing = true;
    private ViewHandler viewHandler;
    private EditUserProfileViewModel editApplicantProfileVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        editApplicantProfileVM = (EditUserProfileViewModel) vm;
        //rootVBox.getChildren().add(editApplicantProfileVM.getList());
        applicantName.textProperty().bindBidirectional(editApplicantProfileVM.applicantNameProperty());
        personalInformation.textProperty().bindBidirectional(editApplicantProfileVM.personalInformationProperty());
        subtitle.textProperty().bindBidirectional(editApplicantProfileVM.subtitleProperty());
        allowEditing();
    }
    private Node list = null;

    @FXML
    private Button EditBtn;

    @FXML
    private VBox rootVBox;

    @FXML
    private TextField applicantName;

    @FXML
    private TextField subtitle;

    @FXML
    private Circle ProfilePicture;

    @FXML
    private TextArea personalInformation;

    @FXML
    void onEditBtn(ActionEvent event) {
        if(isEditing){
            lockEditing();
        }else{
            allowEditing();
        }
    }

    private void allowEditing(){
        EditBtn.setText("Save");
        isEditing = true;
        if (list != null) {
            rootVBox.getChildren().remove(list);
        }
        list = editApplicantProfileVM.getFullList();

        editApplicantProfileVM.setQualityListEditable(true);
        rootVBox.getChildren().add(list);
        applicantName.setEditable(true);
        subtitle.setEditable(true);
        personalInformation.setEditable(true);

    }
    private void lockEditing(){
        EditBtn.setText("Edit");
        isEditing = false;
        editApplicantProfileVM.safeInfoToModel();
        rootVBox.getChildren().remove(list);
        //list = get applicant list
        //rootVBox.getChildren().add(list);
        applicantName.setEditable(false);
        subtitle.setEditable(false);
        personalInformation.setEditable(false);
        viewHandler.openOverview();
    }

}
