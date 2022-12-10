package common.transferObjects;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Applicant implements User, Serializable {
    final char TYPE = 'A';
    private String username;
    private String fullName = "";
    private String subtitle = "";
    private String personalInformation = "";
    private ArrayList<String> qualities = new ArrayList<>();
    private ArrayList<Conversation> conversations = new ArrayList<>();



    public Applicant(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public char getType() {
        return TYPE;
    }

    @Override
    public ArrayList<String> getQualities() {
        return qualities;
    }

    @Override
    public void setFullName(String name) {
        fullName = name;
    }

    @Override
    public void setQualities(ArrayList<String> qualities) {
        this.qualities = qualities;
    }

    @Override
    public String getDetails() {
        return personalInformation;
    }

    @Override
    public void setDetails(String details) {
        personalInformation = details;
    }

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @Override
    public String getSubtitle() {
        return subtitle;
    }

    @Override
    public void updateUser(User newUser) {
        setFullName(newUser.getFullName());
        setDetails(newUser.getDetails());
        setSubtitle(newUser.getSubtitle());
        setQualities(newUser.getQualities());
    }

    @Override
    public void setConvs(ArrayList<Conversation> convs) {
        conversations = convs;
    }

    @Override
    public ArrayList<Conversation> getConvs() {
        return conversations;
    }

    @Override
    public String getConvsId() {
        String result = "";
        for(int i = 0; i<conversations.size(); i++){
            result +="\"" + conversations.get(i).getId() + "\"";
            if(i+1 < conversations.size()){
                result += ",";
            }
        }
        return result;
    }


}
