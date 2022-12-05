package client.view.User;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class ProfileViewModel implements ViewModel {
    ArrayList<String> allQualities = new ArrayList<>();
    ArrayList<String> userQualities = new ArrayList<>();
    private IUserModel clientModel;
    private StringProperty applicantName = new SimpleStringProperty("Full Name");
    private StringProperty subtitle = new SimpleStringProperty("subtitle");
    private StringProperty personalInformation = new SimpleStringProperty("Write something about yourself");

    public ProfileViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        for(int i = 0; i <10 ; i++){
            allQualities.add("Quality " + i);
        }
        if(clientModel.getUserState() != null){
            loadInfoFromModel();
        }


    }
    private void loadInfoFromModel(){
        applicantName = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getFullName());
        subtitle = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getSubtitle());
        personalInformation = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getDetails());
    }

    public void safeInfoToModel(){
        clientModel.getUserState().getUserProfile().setFullName(applicantName.get());
        clientModel.getUserState().getUserProfile().setSubtitle(subtitle.get());
        clientModel.getUserState().getUserProfile().setDetails(personalInformation.get());
        clientModel.getUserState().getUserProfile().setQualities(QualitiesListView.getInstance().getPickedQualities());
    }

    public StringProperty applicantNameProperty() {
        return applicantName;
    }

    public StringProperty subtitleProperty() {
        return subtitle;
    }

    public StringProperty personalInformationProperty() {
        return personalInformation;
    }


    public  ArrayList<String> getQualities(){
        return clientModel.getUserState().getUserProfile().getQualities();
    }

    public  ArrayList<String> getAllQualities(){
        return allQualities;
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
