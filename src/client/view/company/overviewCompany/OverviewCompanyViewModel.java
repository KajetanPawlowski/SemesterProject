package client.view.company.overviewCompany;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;

public class OverviewCompanyViewModel implements ViewModel {
    private IUserModel clientModel;
    public OverviewCompanyViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
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
