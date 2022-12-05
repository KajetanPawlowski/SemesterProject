package client.view.menu;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;

public class MenuViewModel implements ViewModel {
    private IUserModel clientModel;
    public MenuViewModel(IUserModel model){
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
