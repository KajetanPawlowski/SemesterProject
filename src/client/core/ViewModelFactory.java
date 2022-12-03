package client.core;

import client.model.IUserModel;
import client.model.UserModel;
import client.view.User.UserViewModel;
import client.view.chat.ChatViewModel;
import client.view.editUserProfile.EditUserProfileViewModel;
import client.view.jobAd.JobAdViewModel;
import client.view.jobSearch.JobSearchViewModel;
import client.view.login.LoginViewModel;
import client.view.menu.MenuViewModel;
import client.view.menuCompany.MenuCompanyViewModel;
import client.view.overview.OverviewViewModel;

public class ViewModelFactory {
    private IUserModel model= UserModel.getInstance();
    public ViewModelFactory(){
    }

    public IUserModel getModel() {
        return model;
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
    public MenuCompanyViewModel getMenuCompanyVM(){
        return new MenuCompanyViewModel(model);
    }
    public UserViewModel getUserVM(){
        return new UserViewModel(model);
    }
    public JobSearchViewModel getJobSearchVM(){
        return new JobSearchViewModel(model);
    }
    public EditUserProfileViewModel getEditUserVM(){
        return new EditUserProfileViewModel(model);
    }
    public ChatViewModel getChatVM(){
        return new ChatViewModel(model);
    }
    public JobAdViewModel getJobAdVM(){
        return new JobAdViewModel(model);
    }
}
