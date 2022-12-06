package client.view.jobSearch;

import client.core.ViewModel;
import common.transferObjects.JobAdd;
import client.model.IUserModel;
import javafx.application.Platform;

public class JobSearchViewModel implements ViewModel {
    private IUserModel clientModel;
    private JobAdd jobAdd;
    private int noOfAdds;
    private int currentAdd;



    public JobSearchViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
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
        setJobAdd(clientModel.getJobAdds().get(nextAdd));
        currentAdd++;
    }
    private void showPrevAdd(){
        int prevAdd = currentAdd-1;
        System.out.println("JobSearchViewModel::showNextAdd::"+prevAdd);
        if(prevAdd<0){
            prevAdd = noOfAdds;
            currentAdd = noOfAdds;
        }
        setJobAdd(clientModel.getJobAdds().get(prevAdd));
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
