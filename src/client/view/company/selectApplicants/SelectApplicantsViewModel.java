package client.view.company.selectApplicants;

import client.core.ViewModel;
import client.model.IUserModel;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAd;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class SelectApplicantsViewModel implements ViewModel {
    private IUserModel clientModel;
    private Applicant applicant;
    private ArrayList<Applicant> jobAdApplicants;
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
        jobAdApplicants = clientModel.getJobAdApplicants();
    }


    private void showNextApplicant(){
        int nextApplicantIndex = currentApplicantIndex +1;

        if(nextApplicantIndex >= jobAdApplicants.size()){
            nextApplicantIndex = 0;
            currentApplicantIndex = -1;
        }
        setApplicant(jobAdApplicants.get(nextApplicantIndex));
        currentApplicantIndex++;
    }
    private void showPrevApplicant(){
        int prevApplicant = currentApplicantIndex - 1;

        if(prevApplicant < 0){
            prevApplicant = jobAdApplicants.size() - 1;
            currentApplicantIndex = jobAdApplicants.size();
        }
        setApplicant(jobAdApplicants.get(prevApplicant));
        currentApplicantIndex --;
    }

    private void setApplicant(Applicant applicant1){
        for(int i = 0; i< jobAdApplicants.size();i++){
            applicant = jobAdApplicants.get(i);
            fillApplicantInfo();
        }
        applicant=applicant1;

    }

    public void onNextApplicantBtn(){
        showNextApplicant();
    }

    public void onPrevApplicantBtn(){
        showPrevApplicant();
    }

    private void fillApplicantInfo(){
        for(int i =0; i<jobAdApplicants.size(); i++){
            applicantName.setValue(jobAdApplicants.get(i).getFullName());
            subtitle.setValue(jobAdApplicants.get(i).getSubtitle());
            personalInformation.setValue(jobAdApplicants.get(i).getDetails());
        }
    }

    public Applicant getCurrentApplicant(){
        return applicant;
    }

    public StringProperty applicantNameProperty() {
        for(int i =0; i<jobAdApplicants.size(); i++){
            applicantName.setValue(jobAdApplicants.get(i).getFullName());
        }
        return applicantName;
    }
    public StringProperty subtitleProperty() {
        for(int i =0; i<jobAdApplicants.size(); i++){
            subtitle.setValue(jobAdApplicants.get(i).getSubtitle());
        }
        return subtitle;
    }
    public StringProperty personalInformationProperty() {
        for(int i =0; i<jobAdApplicants.size(); i++){
            personalInformation.setValue(jobAdApplicants.get(i).getDetails());
        }
        return personalInformation;
    }
    public StringProperty actionBtnProperty(){
        return actionBtnProperty;
    }
    public ObservableList<String> applicantSkillsList(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i<jobAdApplicants.size(); i++){
            applicant = jobAdApplicants.get(i);
            for(int j = 0; j < applicant.getQualities().size(); j++){
                list.add(applicant.getQualities().get(j));
            }
        }

        ObservableList<String> myObservableList = FXCollections.observableList(list);
        return myObservableList;
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
