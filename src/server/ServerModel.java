package server;

import common.Applicant;
import common.Company;
import common.JobAdd;
import common.User;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Collection;

public class ServerModel  {
    private JDBCConnector database;

    public ServerModel(JDBCConnector database){
        this.database = database;
    }

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Applicant> applicants = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();

    private ArrayList<JobAdd> relevantJobAds = new ArrayList<JobAdd>();


    public void run(){
        try {
            LocateRegistry.createRegistry( 1099 );

            ServerController controller = new ServerController(this);

            Naming.rebind( "server", controller );

            System.out.println( "Server listening on " + InetAddress.getLocalHost().getHostAddress() );

            database.connect("hattie.db.elephantsql.com", 5432, "zdpvllpz", "DkaoNfKGKMNfkg8bVfKyN3pJxPM2GWmn");
//--------------------------------------------------------------SETUP----------------------------------------
            String[] names = {"Kajetan","Maja","Maciej", "Ari","Rado"};
            users = new ArrayList<>();
            for(int i = 0; i <5 ;i++){
                Applicant nextApplicant = new Applicant(names[i]);
                nextApplicant.setFullName( names[i] + " Rasmussen");
                nextApplicant.setSubtitle("Student");
                nextApplicant.setDetails("Hello, \n ny name is " + names[i] + ". Nice to meet you!" );
                users.add(nextApplicant);
                applicants.add(nextApplicant);
            }

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
        return new Company(username);
    }

    public void createNewApplicantUser(Applicant newApplicant) {
        database.insertNewApplicant(newApplicant);
    }

    public void createNewCompanyUser(Company newCompany) {
        database.insertNewCompany(newCompany);
    }

    public void createNewUser(User newUser){
        database.insertNewUser(newUser);
    }

    public ArrayList<Applicant> getAllApplicants(String username){
        applicants.add(getApplicantProfile(username));
        return applicants;
    }

    public ArrayList<Company> getAllCompanies(String username){
        companies.add(getCompanyProfile(username));
        return companies;
    }

    public void createJobAd(JobAdd nextJobAd) {
        database.insertNewJobAdd(nextJobAd);
    }



}
