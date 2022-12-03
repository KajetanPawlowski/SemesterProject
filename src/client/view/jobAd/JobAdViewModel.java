package client.view.jobAd;

import client.core.ViewModel;
import client.model.IUserModel;
import common.JobAdd;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobAdViewModel implements ViewModel {
    private IUserModel model;
    private static JobAdd jobAdd;

    private static StringProperty positionName = new SimpleStringProperty("");
    private static StringProperty subtitle = new SimpleStringProperty("");
    private static StringProperty companyName = new SimpleStringProperty("");
    private static StringProperty jobDescription = new SimpleStringProperty("");
    private static StringProperty requirements = new SimpleStringProperty("");

    public JobAdViewModel(IUserModel model) {
        this.model = model;
    }


    public static void getJobAdInfo(){
        positionName.setValue(jobAdd.getJobTitle());
        companyName.setValue(jobAdd.getCompany().getFullName());
        jobDescription.setValue(jobAdd.getJobDescription());;
    }

    public static void setJobAdInfo(){
        jobAdd.setJobDescription(jobDescription.get());
        jobAdd.setJobTitle(positionName.get());

    }


    public StringProperty positionNameProperty() {
        return positionName;
    }


    public StringProperty subtitleProperty() {
        return subtitle;
    }


    public StringProperty companyNameProperty() {
        return companyName;
    }


    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }


    public StringProperty requirementsProperty() {
        return requirements;
    }
}
