package common.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    private int id;
    private User[] users = new User[2];
    private ArrayList<String> messages = new ArrayList<String>();

    public Conversation(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void addMsg(User sender, String text){
        String nextMsg;
        if(sender.getFullName().equals("")){
            nextMsg = sender.getUsername() +": "+ text;
        }else{
            nextMsg = sender.getFullName()+ ": "+ text;
        }
        messages.add(nextMsg);
    }

    public ArrayList<String> getMessages() {
        return messages;
    }
}
