package client.view.overview;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;

public class OverviewViewModel implements ViewModel {
    private IUserModel clientModel;
    public OverviewViewModel(IUserModel model){
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
