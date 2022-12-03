package client.view.overview;

import client.core.FXMLController;
import client.core.ViewHandler;
import client.core.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class OverviewController implements FXMLController {
    private ViewHandler viewHandler;
    private OverviewViewModel overviewVM;
    private ListViewBtnCustom customList = new ListViewBtnCustom();

    @FXML
    HBox centerHBox;
    @FXML
    VBox listsVBox;

    public void init(ViewHandler viewHandler, ViewModel vm){
        this.viewHandler = viewHandler;
        overviewVM = (OverviewViewModel) vm;
        listsVBox.getChildren().addAll(customList.createList(10, "Jobs you applied for: "), customList.createList(15, "Jobs you might be interested in: "));
        centerHBox.getChildren().add(viewHandler.getUserProfile());
    }


}
