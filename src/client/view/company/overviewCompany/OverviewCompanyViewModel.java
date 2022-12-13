package client.view.company.overviewCompany;

import client.core.ViewModel;
import client.model.IUserModel;
import client.view.applicant.overview.ListViewBtnCustom;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class OverviewCompanyViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty titleLabelProperty;
    private StringProperty companyDescriptionProperty;
    public OverviewCompanyViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        titleLabelProperty = new SimpleStringProperty("Welcome " + clientModel.getUser().getUsername());
        companyDescriptionProperty = new SimpleStringProperty(clientModel.getUser().getDetails());

    }

    public StringProperty getTitleLabelProperty() {
        return titleLabelProperty;
    }
    public StringProperty getCompanyDescriptionProperty(){return companyDescriptionProperty;}

    public Node getJobAddList(){
        ListViewBtnCustom list = new ListViewBtnCustom();
        return list.createList(clientModel.getClientJobAds(), "Your open JobAds");
    }

    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //UPDATE CODE
            }
        });
    }
}
