package server;

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

    private ArrayList<JobAd> relevantJobAds = new ArrayList<JobAd>();


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
//--------------------------------------------------------------SETUP----------------------------------------
            String[] names = {"Kajetan","Maja","Maciej", "Ari","Rado","Kamstrup", "LEGO", "Arla Foods", "Wolt"};
            users = new ArrayList<>();
            int i = 0;
            for(; i <5 ;i++){
                Applicant nextApplicant = new Applicant(names[i]);
                nextApplicant.setFullName( names[i] + " Rasmussen");
                nextApplicant.setSubtitle("Student");
                nextApplicant.setDetails("Hello, \n ny name is " + names[i] + ". Nice to meet you!" );
                users.add(nextApplicant);
                applicants.add(nextApplicant);
            }
            for(; i < names.length ;i++){
                Company nextCompany = new Company(names[i]);
                nextCompany.setFullName( names[i] + " Company");
                nextCompany.setSubtitle("Company");
                nextCompany.setDetails("Nice to meet you!" );
                users.add(nextCompany);
                companies.add(nextCompany);
            }

//            for(int j = 0; j < 10; j++){
//                qualities.add("Skill " + j);
//            }

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

    public Applicant getApplicantProfile(String username) {
        //Applicant applicantProfile = database.getApplicantProfile(username);
        //return applicantProfile;
        //write the code for DATABASE
        for(int i = 0; i < applicants.size(); i++){
            if(applicants.get(i).getUsername().equals(username)){
                return applicants.get(i);
            }
        }
        return null;
    }

    public Company getCompanyProfile(String username) {
        //Company companyProfile = database.getCompanyProfile(username);
        //return companyProfile;
        //write the code for DATABASE
        for(int i = 0; i < companies.size(); i++){
            if(companies.get(i).getUsername().equals(username)){
                return companies.get(i);
            }
        }
        return null;
    }

    public void createNewApplicantUser(Applicant newApplicant) {
        createNewUser(newApplicant);
        applicants.add(newApplicant);
        LogBook.getInstance().quickServerLog("ServerModel::createNewApplicantUser::"+newApplicant.getUsername());
        //database.insertNewApplicant(newApplicant);
    }

    public void createNewCompanyUser(Company newCompany) {
        createNewUser(newCompany);
        companies.add(newCompany);
        LogBook.getInstance().quickServerLog("ServerModel::createNewCompanyUser::"+newCompany.getUsername());
        //database.insertNewCompany(newCompany);
    }

    public void createNewUser(User newUser){
        users.add(newUser);
        //database.insertNewUser(newUser);
    }

    public void updateUser(User newUser){
        LogBook.getInstance().quickServerLog("ServerModel::updateUser");
        getUser(newUser.getUsername()).updateUser(newUser);

    }
    public User getUser(String username){
        for(int i = 0; i < users.size(); i ++){
            if(users.get(i).getUsername().equals(username)){
                return users.get(i);
            }
        }
        return null;
    }
    public ArrayList<String> getQualities(){
        return database.getAllQualities();
    }
    public void insertNewQuality(String nextQuality){
        database.insertQuality(nextQuality);
    }



    public ArrayList<Applicant> getAllApplicants(String username){
        applicants.add(getApplicantProfile(username));
        return applicants;
    }

    public ArrayList<Company> getAllCompanies(String username){
        companies.add(getCompanyProfile(username));
        return companies;
    }

    public void createJobAd(JobAd nextJobAd) {
        database.insertNewJobAdd(nextJobAd);
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
                break;
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



}
