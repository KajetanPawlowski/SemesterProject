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
    private ArrayList<Applicant> applicants = new ArrayList<Applicant>();
    private ArrayList<Company> companies = new ArrayList<Company>();
    private ArrayList<JobAdd> relevantJobAds = new ArrayList<JobAdd>();


    public void run(){
        try {
            LocateRegistry.createRegistry( 1099 );

            ServerController controller = new ServerController(this);

            Naming.rebind( "server", controller );

            System.out.println( "Server listening on " + InetAddress.getLocalHost().getHostAddress() );

            database.connect("hattie.db.elephantsql.com", 5432, "zdpvllpz", "DkaoNfKGKMNfkg8bVfKyN3pJxPM2GWmn");

        } catch( Exception ex ) {
            ex.printStackTrace();
        }
    }

    public char getUserType(String username) {
        char type = database.getUserType(username);
        return type;
    }

    public Applicant getApplicantProfile(String username) {
        Applicant applicantProfile = database.getApplicantProfile(username);
        return applicantProfile;
    }

    public Company getCompanyProfile(String username) {
        Company companyProfile = database.getCompanyProfile(username);
        return companyProfile;
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


    public ArrayList<JobAdd> getRelevantJobAds(Applicant applicant, JobAdd relevantJobAd) {
        if(applicant.getEducation().equals(relevantJobAd.getRequirements())) {
            relevantJobAds.add(relevantJobAd);
        }
        return relevantJobAds;
    }
}
