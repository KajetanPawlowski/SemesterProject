package client.view.menuCompany;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuCompanyController implements FXMLController {
    public ViewHandler viewHandler;
    public MenuViewModel menuVM;

    @FXML
    Button overviewBtn;
    @FXML
    Button editProfileBtn;
    @FXML
    Button jobAdsBtn;
    @FXML
    Button chatBtn;
    @FXML
    Button logoutBtn;
    @FXML
    void onChatBtn(ActionEvent event) {

    }

    @FXML
    void onEditProfileBtn(ActionEvent event) {

    }

    @FXML
    void onJobAdsBtn(ActionEvent event) {

    }

    @FXML
    void onLogoutBtn(ActionEvent event) {
        viewHandler.openLoginView();
    }

    @FXML
    void onOverviewBtn(ActionEvent event) {
        viewHandler.openOverview();
    }



    public void init(ViewHandler viewHandler, ViewModel menuVM) {
        this.viewHandler = viewHandler;
        this.menuVM = (MenuViewModel)menuVM;
    }
}