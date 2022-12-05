package client.view.editUserProfile;

import client.core.ViewModel;
import client.model.IUserModel;
import client.view.User.QualitiesListView;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

import java.util.ArrayList;

public class EditUserProfileViewModel implements ViewModel {
    private IUserModel clientModel;
    private StringProperty applicantName = new SimpleStringProperty("");
    private StringProperty subtitle = new SimpleStringProperty("");
    private StringProperty personalInformation = new SimpleStringProperty("");
    private QualitiesListView qualityList = QualitiesListView.getInstance();
    public EditUserProfileViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        loadInfoFromModel();
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


    private void loadInfoFromModel(){
        applicantName = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getFullName());
        subtitle = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getSubtitle());
        personalInformation = new SimpleStringProperty(clientModel.getUserState().getUserProfile().getDetails());
    }

    public void safeInfoToModel(){
        clientModel.getUserState().getUserProfile().setFullName(applicantName.get());
        clientModel.getUserState().getUserProfile().setSubtitle(subtitle.get());
        clientModel.getUserState().getUserProfile().setDetails(personalInformation.get());
        //clientModel.getUserState().getUserProfile().setQualities(qualityList.getPickedQualities());
    }
    public Node getFullList(){
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            temp.add("Skill " + i);
        }
        return qualityList.createCheckedList("Skills", temp);
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
