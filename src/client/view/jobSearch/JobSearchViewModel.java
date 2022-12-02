package client.view.jobSearch;

import client.core.ViewModel;
import common.JobAdd;
import client.model.IUserModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JobSearchViewModel implements ViewModel {
    private IUserModel model;
    private JobAdd jobAdd;
    private int noOfAdds;
    private int currentAdd;



    public JobSearchViewModel(IUserModel model){
        this.model = model;
        noOfAdds = 0;
        if(noOfAdds>0){
            setJobAdd(model.getJobAdds().get(0));
            currentAdd = 0;
        }
    }

    private void showNextAdd(){
        int nextAdd = currentAdd+1;
        System.out.println("JobSearchViewModel::showNextAdd::"+nextAdd);
        if(nextAdd>noOfAdds){
            nextAdd = 0;
            currentAdd = 0;
        }
        setJobAdd(model.getJobAdds().get(nextAdd));
        currentAdd++;
    }
    private void showPrevAdd(){
        int prevAdd = currentAdd-1;
        System.out.println("JobSearchViewModel::showNextAdd::"+prevAdd);
        if(prevAdd<0){
            prevAdd = noOfAdds;
            currentAdd = noOfAdds;
        }
        setJobAdd(model.getJobAdds().get(prevAdd));
        currentAdd--;
    }
    private void setJobAdd(JobAdd add){
        jobAdd = add;
        //fillJobInfo();
    }

    public void onNextAddBtn(){
        showNextAdd();
    }

    public void onPrevAddBtn(){
        showPrevAdd();
    }



}
