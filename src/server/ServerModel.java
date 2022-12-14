package server;

import client.model.UserNotFoundException;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAd;
import common.transferObjects.User;
import common.util.LogBook;
import common.util.UserAlreadyConnectedException;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class ServerModel {
    private JDBCConnector database;

    public ServerModel(JDBCConnector database){
        this.database = database;
    }
    private ArrayList<User> connectionPool = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();
    private ArrayList<String> qualities = new ArrayList<>();



    public void run(){
        try {
            LocateRegistry.createRegistry( 1099 );

            ServerController controller = new ServerController(this);

            Naming.rebind( "server", controller );

            System.out.println( "Server listening on " + InetAddress.getLocalHost().getHostAddress() );

            try{
                database.connect("hattie.db.elephantsql.com", 5432, "zdpvllpz", "DkaoNfKGKMNfkg8bVfKyN3pJxPM2GWmn");
            }catch (ConnectionFailedException ex){
                System.exit(2);
            }

            qualities = database.getAllQualities();

        } catch( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public char getUserType(String username) {
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                return users.get(i).getType();
            }
        }
        return 0;

    }


    public void createNewUser(User newUser){
        database.insertNewUser(newUser);
        connectionPool.add(newUser);
    }

    public void updateUser(User newUser){
        database.updateUser(newUser);
        LogBook.getInstance().quickServerLog("ServerModel::updateUser");

    }
    public User getUser(String username) throws UserNotFoundException {
        return database.getUser(username);
    }

    public ArrayList<String> getQualities(){
        return database.getAllQualities();
    }
    public void insertNewQuality(String nextQuality){
        database.insertQuality(nextQuality);
    }

    public void createJobAd(JobAd nextJobAd) {
        database.insertNewJobAdd(nextJobAd);
    }

    public ArrayList<JobAd> getRelevantJobAds(User user){
        ArrayList<JobAd> jobAds = database.getAllJobAds();
        if(user.getType() == 'A'){
            ArrayList<JobAd> applied = getAppliedJobs(user);
            for(int i = 0; i < applied.size(); i++){
                jobAds.remove(applied.remove(i));
            }
            return jobAds;
        }else{
            return getCreatedAds(user, jobAds);
        }
    }

    private ArrayList<JobAd> getCreatedAds(User user, ArrayList<JobAd> allJobAds){
        ArrayList<JobAd> result = new ArrayList<JobAd>();
        for(int i = 0; i < allJobAds.size(); i++){
            if(allJobAds.get(i).getCompany().getUsername().equals(user.getUsername())){
                result.add(allJobAds.get(i));
            }
        }
        return result;
    }
    public ArrayList<JobAd> getAppliedJobs(User user){
        ArrayList<JobAd> allJobAds = database.getAllJobAds();
        ArrayList<JobAd> result = new ArrayList<JobAd>();
        for(int i = 0; i < allJobAds.size(); i++){
            for(int j = 0; j < allJobAds.get(i).getApplicants().size(); j++){
                if(allJobAds.get(i).getApplicants().get(j).getUsername().equals(user.getUsername())){
                    result.add(allJobAds.get(i));
                }
            }
        }
        return result;
    }

    public ArrayList<Applicant> getJobAdApplicants(User user){
        ArrayList<Applicant> jobAdApplicants = new ArrayList<>();
        for(int i =0; i<database.getAllJobAds().size(); i++){
            jobAdApplicants = database.getAllJobAds().get(i).getApplicants();
        }
        return jobAdApplicants;
    }

    public void updateJobAd(JobAd jobAd){

        database.updateJobAd(jobAd);
    }

    public void openConnection(String username) throws UserAlreadyConnectedException {
        checkIfAlreadyConnected(username);
        connectionPool.add(database.getUser(username));
    }
    public void closeConnection(String username){
        for (int i = 0; i < connectionPool.size(); i++){
            if(connectionPool.get(i).equals(username)){
                connectionPool.remove(connectionPool.get(i));
                LogBook.getInstance().quickServerLog("ServerModel::CloseConnection::" + username);
                if(connectionPool.size()== 0){
                    database.close();
                }
            }
        }
    }
    private void checkIfAlreadyConnected(String username)throws UserAlreadyConnectedException{
        for (int i = 0; i < connectionPool.size(); i++){
            if(connectionPool.get(i).equals(username)){
                throw new UserAlreadyConnectedException();
            }
        }
    }

    public int createNewConversation(User user1, User user2){
        return 0;
    }




}
