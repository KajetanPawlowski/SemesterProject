package client.model;

import common.transferObjects.Applicant;
import common.transferObjects.JobAd;
import common.transferObjects.User;
import common.util.Subject;
import common.util.UserAlreadyConnectedException;

import java.util.ArrayList;

public interface IUserModel extends Subject {
    boolean connectToServer(String username, String ip) throws UserNotFoundException, UserAlreadyConnectedException;
    void disconnectFromServer(String username);
    void updateUser();
    void createNewUser(String username, char type);
    void createNewJobAd(JobAd nextJobAd);
    void updateJobAd(JobAd nextJobAd);
    User getUser();
    ArrayList<JobAd> getClientJobAds();
    ArrayList<JobAd> getAppliedJobAds();
    ArrayList<Applicant> getJobAdApplicants(JobAd jobad);
    void resetModel();
    ArrayList<String> getAllQualities();
    void addNewQuality(String quality);
    void applyForJob(JobAd jobAd);




}
