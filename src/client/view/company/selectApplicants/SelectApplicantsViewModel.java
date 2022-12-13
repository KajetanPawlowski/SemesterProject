package client.view.company.selectApplicants;

import client.core.ViewModel;
import client.model.IUserModel;
import common.transferObjects.Applicant;
import common.transferObjects.JobAd;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SelectApplicantsViewModel implements ViewModel {
    private IUserModel clientModel;
    private Applicant applicant;
    private JobAd jobAd;
    private int currentApplicantIndex;

    private StringProperty applicantName= new SimpleStringProperty("");
    private  StringProperty subtitle= new SimpleStringProperty("");
    private  StringProperty personalInformation= new SimpleStringProperty("");
    private  StringProperty skills= new SimpleStringProperty("");
    private  StringProperty actionBtnProperty= new SimpleStringProperty("");

    public SelectApplicantsViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);


    }


    public StringProperty positionNameProperty() {
        return applicantName;
    }
    public StringProperty companyNameProperty() {
        return subtitle;
    }
    public StringProperty jobDescriptionProperty() {
        return personalInformation;
    }
    public StringProperty actionBtnProperty(){
        return actionBtnProperty;
    }
    public ObservableList<String> applicantSkillsList(){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < applicant.getQualities().size(); i++){
            list.add(applicant.getQualities().get(i));
        }
        ObservableList<String> myObservableList = FXCollections.observableList(list);
        return myObservableList;
    }

    @Override
    public void update() {

    }
}
