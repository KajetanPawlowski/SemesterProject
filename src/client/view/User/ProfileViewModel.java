package client.view.User;

import client.core.ViewModel;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

import java.util.ArrayList;

public class ProfileViewModel implements ViewModel {
    ArrayList<String> userQualities = new ArrayList<>();
    private IUserModel clientModel;
    private StringProperty applicantName = new SimpleStringProperty("Full Name");
    private StringProperty subtitle = new SimpleStringProperty("subtitle");
    private StringProperty personalInformation = new SimpleStringProperty("Write something about yourself");

    public ProfileViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        if(clientModel.getUserState() != null){
            loadInfoFromModel();
        }


    }
    private void loadInfoFromModel(){
        applicantName = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getFullName());
        subtitle = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getSubtitle());
        personalInformation = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getDetails());
    }
     public char getUserType(){
        return clientModel.getUserState().getUserProfile().getType();
     }

     public Node getApplicantList(){
         return QualitiesListView.getInstance().createCheckedList("Skills", null, clientModel.getUserState().getUserProfile().getQualities());
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
        return clientModel.getAllQualities();
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
