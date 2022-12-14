package common.transferObjects;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    private int id;
    private ArrayList<User> users = new ArrayList<>();
    private int jobId;

    private ArrayList<String> messages = new ArrayList<String>();

    public Conversation(int id){
        this.id = id;
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

    public ArrayList<String> getAllMessages() {
        return messages;
    }

    public int getId(){
        return id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public int getJobId() {
        return jobId;
    }
    public void setJobId(int newId){
        jobId = newId;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
