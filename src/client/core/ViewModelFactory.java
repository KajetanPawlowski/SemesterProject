package client.core;

import client.model.UserModel;
import client.view.applicant.ApplicantViewModel;
import client.view.editApplicantProfile.EditApplicantProfileViewModel;
import client.view.jobSearch.JobSearchViewModel;
import client.view.login.LoginViewModel;
import client.view.menu.MenuViewModel;
import client.view.overview.OverviewViewModel;

public class ViewModelFactory {
    public ViewModelFactory(){
    }
    public LoginViewModel getLoginVM(){
        return new LoginViewModel(UserModel.getInstance());
    }
    public OverviewViewModel getOverviewVM() {
        return new OverviewViewModel(UserModel.getInstance());
    }
    public MenuViewModel getMenuVM(){
        return new MenuViewModel(UserModel.getInstance());
    }
    public ApplicantViewModel getApplicantVM(){
        return new ApplicantViewModel(UserModel.getInstance());
    }
    public JobSearchViewModel getJobSearchVM(){
        return new JobSearchViewModel(UserModel.getInstance());
    }
    public EditApplicantProfileViewModel getEditApplicantVM(){return new EditApplicantProfileViewModel(mf.getClientModel());}
}
