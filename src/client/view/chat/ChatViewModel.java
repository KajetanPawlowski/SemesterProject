package client.view.chat;


import client.core.ViewModel;

import client.model.IUserModel;
import common.transferObjects.Conversation;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ChatViewModel implements ViewModel {
    private IUserModel clientModel;

    private StringProperty messageInputProperty = new SimpleStringProperty("");
    private Conversation currentConversation;
    private ObservableList<String> messageList;
    private ObservableList<String> accountList;
    


    public ChatViewModel(IUserModel model){
        clientModel = model;
        clientModel.attachObserver(this);
        messageList = FXCollections.observableArrayList();
        accountList = FXCollections.observableArrayList();
        openConversation(clientModel.getUser().getUsername());
        
    }

    public StringProperty getMessageInputProperty(){
        return messageInputProperty;
    }
    public ObservableList<String> getMessagesList(){
        return messageList;
    }
    public ObservableList<String> getAccountsList(){
        return accountList;
    }
    
    public void openConversation(String username){
        ArrayList<Conversation> conversations = clientModel.getUser().getConvs();
        for(int i = 0; i < conversations.size(); i++){
            if(conversations.get(i).getUsers().get(0).getUsername().equals(username)){
                currentConversation = conversations.get(i);
            }else if(conversations.get(i).getUsers().get(1).getUsername().equals(username)){
                currentConversation = conversations.get(i);
            }
        }
    }
    
    
    public void sentMessage(){
        System.out.println("ChatViewModel::sentMessage");
        currentConversation.addMsg(clientModel.getUser(), messageInputProperty.get());
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
        for(int i = 0; i < clientModel.getAllConversationBuddies().size();i++){
            accountList.add(clientModel.getAllConversationBuddies().get(i));
        }
    }
    @Override
    public void update() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                updateMessageList();
                updateAccountList();
            }
        });
    }



}
