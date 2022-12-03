package client.view.User;

import client.core.ViewModel;
import client.model.IUserModel;

import java.util.ArrayList;

public class UserViewModel implements ViewModel {
    ArrayList<String> qualities = new ArrayList<>();
    public UserViewModel(IUserModel model){
        for(int i = 0; i <10 ; i++){
            qualities.add("Quality " + i);
        }

    }
    public  ArrayList<String> getQualities(){
        return qualities;
    }


}
