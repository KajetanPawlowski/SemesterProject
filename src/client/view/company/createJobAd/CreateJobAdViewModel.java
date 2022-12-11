package client.view.company.createJobAd;

import client.core.ViewModel;
import client.model.IUserModel;
import client.view.applicant.editUserProfile.QualitiesListView;
import common.transferObjects.JobAd;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class CreateJobAdViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty jobTitle = new SimpleStringProperty("");
    private StringProperty companyName;
    private StringProperty jobDescription = new SimpleStringProperty("");
    private StringProperty nextQuality = new SimpleStringProperty("");
    private QualitiesListView qualityList = new QualitiesListView();

    public CreateJobAdViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        companyName = new SimpleStringProperty(clientModel.getUser().getFullName());
    }

    public Node getFullList(){
        qualityList = new QualitiesListView();
        return qualityList.createCheckedList("Requirements", clientModel.getAllQualities(), null);
    }

    public void setQualityListEditable(boolean isEditable){
        qualityList.setEditable(isEditable);
    }


    public StringProperty jobTitleProperty() {
        return jobTitle;
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }

    public StringProperty nextQualityProperty() {
        return nextQuality;
    }

    public void createNewAd(){
        JobAd nextAd = new JobAd(jobTitle.get(), clientModel.getUser(), jobDescription.get(), qualityList.getPickedQualities());
        clientModel.createNewJobAd(nextAd);

    }

    public void addQuality(){
        System.out.println("addQuality::" + nextQuality.get());
        clientModel.addNewQuality(nextQuality.get());
        nextQuality.setValue("");
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
