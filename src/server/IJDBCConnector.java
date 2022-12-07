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

    User getUser(String username);
    ArrayList<String> getAllQualities ();




}
