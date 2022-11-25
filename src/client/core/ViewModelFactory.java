package client.core;

import client.model.IUserModel;
import client.model.UserModel;
import client.view.applicant.ApplicantViewModel;
import client.view.chat.ChatViewModel;
import client.view.editApplicantProfile.EditApplicantProfileViewModel;
import client.view.jobSearch.JobSearchViewModel;
import client.view.login.LoginViewModel;
import client.view.menu.MenuViewModel;
import client.view.overview.OverviewViewModel;

public class ViewModelFactory {
    private IUserModel model= UserModel.getInstance();
    public ViewModelFactory(){
    }
    public LoginViewModel getLoginVM(){
        return new LoginViewModel(model);
    }
    public OverviewViewModel getOverviewVM() {
        return new OverviewViewModel(model);
    }
    public MenuViewModel getMenuVM(){
        return new MenuViewModel(model);
    }
    public ApplicantViewModel getApplicantVM(){
        return new ApplicantViewModel(model);
    }
    public JobSearchViewModel getJobSearchVM(){
        return new JobSearchViewModel(model);
    }
    public EditApplicantProfileViewModel getEditApplicantVM(){
        return new EditApplicantProfileViewModel(model);
    }
    public ChatViewModel getChatVM(){
        return new ChatViewModel(model);
    }
}
