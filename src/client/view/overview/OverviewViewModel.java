package client.view.overview;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OverviewViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty titleLabelProperty;
    public OverviewViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
            titleLabelProperty = new SimpleStringProperty("Welcome " + clientModel.getUser().getUsername());
    }

    public StringProperty getTitleLabelProperty() {
        return titleLabelProperty;
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
