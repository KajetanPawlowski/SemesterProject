package server;

import common.transferObjects.Conversation;
import common.transferObjects.JobAdd;
import common.transferObjects.User;

import java.util.ArrayList;

public interface IJDBCConnector {
    void connect(String host, int portNo, String userName, String password);
    void close();

    void insertNewUser(User user);
    void insertNewJobAdd(JobAdd jobAd);
    void insertQuality(String quality);
    int insertConversation(Conversation conversation);

    User getUser(String username);
    ArrayList<String> getAllQualities ();
    ArrayList<JobAdd> getAllJobAds();



//    new Conversation(COmpany, Applicant, JobId)
//    int id;
//    public  Conversation(COmpany, Applicant, JobId){
//        id = insertConversation(this)
//    }





}
