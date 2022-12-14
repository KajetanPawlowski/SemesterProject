package server;

import client.model.UserNotFoundException;
import common.transferObjects.Applicant;
import common.transferObjects.Conversation;
import common.transferObjects.JobAd;
import common.transferObjects.User;

import java.util.ArrayList;

public interface IJDBCConnector {
    void connect(String host, int portNo, String userName, String password) throws ConnectionFailedException;
    void close();

    void insertNewUser(User user);
    void insertNewJobAdd(JobAd jobAd);
    void insertQuality(String quality);
    int insertConversation(User applicant, JobAd jobAd);

    void updateUser(User user);
    void updateJobAd(JobAd jobAd);

    User getUser(String username) throws UserNotFoundException;
    ArrayList<String> getAllQualities ();
    ArrayList<JobAd> getAllJobAds();
    ArrayList<Conversation> getConversations(ArrayList<Integer> convIds);
    //ArrayList<Applicant> getJobAdApplicants (JobAd jobAd);

    void truncateAllTables();
    void truncateQualitiesTable();

}
