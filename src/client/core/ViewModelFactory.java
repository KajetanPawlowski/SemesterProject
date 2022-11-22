package client.core;

import client.view.applicant.ApplicantViewModel;
import client.view.jobSearch.JobSearchViewModel;
import client.view.login.LoginViewModel;
import client.view.menu.MenuViewModel;
import client.view.overview.OverviewViewModel;

public class ViewModelFactory {
    private ModelFactory mf;
    public ViewModelFactory(ModelFactory mf){
        this.mf = mf;
    }
    public LoginViewModel getLoginVM(){
        return new LoginViewModel(mf.getClientModel());
    }
    public OverviewViewModel getOverviewVM() {
        return new OverviewViewModel(mf.getClientModel());
    }
    public MenuViewModel getMenuVM(){
        return new MenuViewModel(mf.getClientModel());
    }
    public ApplicantViewModel getApplicantVM(){
        return new ApplicantViewModel(mf.getClientModel());
    }
    public JobSearchViewModel getJobSearchVM(){
        return new JobSearchViewModel(mf.getClientModel());
    }
}
