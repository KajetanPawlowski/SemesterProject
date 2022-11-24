package client.view.chat;


import client.core.ViewModel;

import client.model.IUserModel;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ChatViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty messageInputProperty = new SimpleStringProperty("");
    private StringProperty accountNameLabelProperty = new SimpleStringProperty("");
    private ObservableList<String> messageList;
    private ObservableList<String> accountList;
    


    public ChatViewModel(IUserModel model){
        clientModel = model;
        accountNameLabelProperty.set("Hi " );
        messageList = FXCollections.observableArrayList();
        accountList = FXCollections.observableArrayList();
        
    }

    public StringProperty getMessageInputProperty(){
        return messageInputProperty;
    }
    public StringProperty getAccountNameLabelProperty(){
        return accountNameLabelProperty;
    }
    public ObservableList<String> getMessagesList(){
        return messageList;
    }
    public ObservableList<String> getAccountsList(){
        return accountList;
    }
    
    
    
    
    public void sentMessage(){
        System.out.println("ChatViewModel::sentMessage");
        messageInputProperty.setValue("");

    }
    

    public void updateMessageList(){
        messageList.clear();
//        for(int i = 0; i < clientModel.getMessagesArrayList().size(); i++) {
//            messageList.add(clientModel.getMessagesArrayList().get(i));
//        }

    }
    public void updateAccountList(){
        accountList.clear();
//        for(int i = 0; i < clientModel.getAccountArrayList().size();i++){
//            accountList.add(clientModel.getAccountArrayList().get(i));
//        }
    }



}
