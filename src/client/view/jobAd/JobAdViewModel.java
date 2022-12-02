package client.view.jobAd;

import client.core.ViewModel;
import client.model.IUserModel;
import common.JobAdd;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobAdViewModel implements ViewModel {
    private IUserModel model;
    private JobAdd jobAdd;

    private StringProperty positionName = new SimpleStringProperty("");
    private StringProperty subtitle = new SimpleStringProperty("");
    private StringProperty companyName = new SimpleStringProperty("");
    private StringProperty jobDescription = new SimpleStringProperty("");
    private StringProperty requirements = new SimpleStringProperty("");

    public JobAdViewModel(IUserModel model) {
        this.model = model;
    }


    private void fillJobInfo(){
        positionName.setValue(jobAdd.getJobTitle());
        companyName.setValue(jobAdd.getCompany().getCompanyName());
        jobDescription.setValue(jobAdd.getJobDescription());
        requirements.setValue(jobAdd.getJobDescription());
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
