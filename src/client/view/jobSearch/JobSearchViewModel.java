package client.view.jobSearch;

import client.core.ViewModel;
import client.view.applicant.overview.ListViewBtnCustom;
import common.transferObjects.JobAd;
import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class JobSearchViewModel implements ViewModel {
    private IUserModel clientModel;
    private JobAd jobAdd;
    private ArrayList<JobAd> allJobAds;
    private int currentAdd;

    private  StringProperty positionName= new SimpleStringProperty("");
    private  StringProperty companyName= new SimpleStringProperty("");
    private  StringProperty jobDescription= new SimpleStringProperty("");
    private  StringProperty companyDescription= new SimpleStringProperty("");


    public JobSearchViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        allJobAds = clientModel.getClientJobAds();
        setJobAdd(allJobAds.get(0));
    }

    private void showNextAdd(){
        int nextAdd = currentAdd +1;

        if(nextAdd >= allJobAds.size()){
            nextAdd = 0;
            currentAdd = -1;
        }
        setJobAdd(allJobAds.get(nextAdd));
        currentAdd++;
    }
    private void showPrevAdd(){
        int prevAdd = currentAdd - 1;

        if(prevAdd < 0){
            prevAdd = allJobAds.size() - 1;
            currentAdd = allJobAds.size();
        }
        setJobAdd(allJobAds.get(prevAdd));
        currentAdd --;
    }
    private void setJobAdd(JobAd add){
        jobAdd = add;
        fillJobInfo();
    }

    public void onNextAddBtn(){
        showNextAdd();
    }

    public void onPrevAddBtn(){
        showPrevAdd();
    }

    private void fillJobInfo(){
        positionName.setValue(jobAdd.getJobTitle());
        companyName.setValue(jobAdd.getCompany().getFullName());
        jobDescription.setValue(jobAdd.getJobDescription());
        companyDescription.setValue(jobAdd.getCompany().getDetails());
    }

    public StringProperty positionNameProperty() {
        return positionName;
    }
    public StringProperty companyNameProperty() {
        return companyName;
    }
    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }
    public StringProperty companyDescriptionProperty() {
        return companyDescription;
    }
    public ObservableList<String> jobRequirementsList(){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < jobAdd.getRequirements().size(); i++){
            list.add(jobAdd.getRequirements().get(i));
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
