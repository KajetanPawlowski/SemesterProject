package client.view.jobSearch;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class JobSearchController implements FXMLController {
    private ViewHandler viewHandler;
    private JobSearchViewModel jobSearchVM;

    @Override
    public void init(ViewHandler vh, ViewModel vm) {
        viewHandler = vh;
        jobSearchVM = (JobSearchViewModel)vm;
        positionNameLabel.textProperty().bindBidirectional(jobSearchVM.positionNameProperty());
        subtitleLabel.textProperty().bindBidirectional(jobSearchVM.companyNameProperty());
        jobDescriptionField.textProperty().bindBidirectional(jobSearchVM.jobDescriptionProperty());
        requirementsField.textProperty().bindBidirectional(jobSearchVM.requirementsProperty());

    }

    @FXML
    private Button ApplyNowBtn;

    @FXML
    private TextArea jobDescriptionField;

    @FXML
    private Button nextAddBtn;

    @FXML
    private Circle pictureCircle;

    @FXML
    private Label positionNameLabel;

    @FXML
    private Button prevAddBtn;

    @FXML
    private TextArea requirementsField;

    @FXML
    private Label subtitleLabel;

    @FXML
    void onApplyNowBtn(ActionEvent event) {

    }

    @FXML
    void onNextAddBtn(ActionEvent event) {
        jobSearchVM.onNextAddBtn();
    }

    @FXML
    void onPrevAddBtn(ActionEvent event) {
        jobSearchVM.onPrevAddBtn();
    }
}
