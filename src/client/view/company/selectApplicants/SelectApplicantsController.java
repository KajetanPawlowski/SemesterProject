package client.view.company.selectApplicants;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class SelectApplicantsController implements FXMLController {
    private ViewHandler viewHandler;
    private SelectApplicantsViewModel searchApplicantsVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        searchApplicantsVM = (SelectApplicantsViewModel)vm;

        applicantName.textProperty().bindBidirectional(searchApplicantsVM.positionNameProperty());
        subtitle.textProperty().bindBidirectional(searchApplicantsVM.companyNameProperty());
        personalInformation.textProperty().bindBidirectional(searchApplicantsVM.jobDescriptionProperty());
        acceptApplicantBtn.textProperty().bindBidirectional(searchApplicantsVM.actionBtnProperty());
        loadSkillsList();
    }

    @FXML
    private Button nextApplicantBtn;

    @FXML
    private Button acceptApplicantBtn;

    @FXML
    private Label applicantName;

    @FXML
    private Label subtitle;

    @FXML
    private TextArea personalInformation;

    @FXML
    private ListView<String> skills;

    private void loadSkillsList(){
        skills.setItems(searchApplicantsVM.applicantSkillsList());

    }
}
