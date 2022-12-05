package client.view.menuCompany;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;

public class MenuCompanyViewModel implements ViewModel {
    private IUserModel clientModel;
    public MenuCompanyViewModel(IUserModel model){
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
