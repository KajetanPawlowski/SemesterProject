package server;

import client.model.UserNotFoundException;
import common.transferObjects.Applicant;
import common.transferObjects.Company;
import common.transferObjects.JobAdd;
import common.transferObjects.User;
import org.postgresql.util.PSQLException;

import java.sql.*;

//KAJETAN WAS HERE HHEHEHEHEHEHE

public class JDBCConnector {
    private Connection connection;

    public void connect(String host, int portNo, String userName, String password) {
        // Establishing a PostgreSQL database connection
        String databaseUrl = "jdbc:postgresql://" + host + ":" + portNo + "/" + userName;

        try {
            connection = DriverManager.getConnection(databaseUrl, userName, password);
            System.out.println("Connection established to: " + databaseUrl);
        } catch (PSQLException exception) {

            System.out.println("Connection failed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//-----------------------------------------------------------------------------------------------------------User OPERATIONS
    // Stores a new User in the DB
    public void insertNewUser(User user){
        String SQL = "INSERT INTO sep5.User VALUES "
                + "(DEFAULT, '" + user.getUsername() + "', '" + user.getType() +"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    // Gets the user type
    public char getUserType(String username) throws UserNotFoundException{
        String SQL = "SELECT type FROM sep5.User WHERE username = '"+username+"';";
        ResultSet rs;
        char result = 0;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
            result =  rs.getString(1).charAt(0);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if(result == 0){
            throw new UserNotFoundException();
        }
        return result;
    }


//-----------------------------------------------------------------------------------------------------------Applicant OPERATIONS
    // Stores a new Applicant in the DB
    public void insertNewApplicant(Applicant applicant) {
        //insertNewUser(applicant);
        //String SQL = "INSERT INTO sep5.Applicant VALUES "
        //        + "( '" + applicant.getUsername() + "','" + applicant.getFullName() + "', '" + applicant.getPersonalInformation() +"', '"
        //+ applicant.getContact()+"', '"+applicant.getEducation()+"' , '"+applicant.getLanguages()+"' , '"
        //        +applicant.getExperience()+"' , '"+applicant.getSkills()+"');";
        try {
            Statement statement = connection.createStatement();
        //    statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Updates the table Applicant in the DB
    public void updateApplicant(String name, String attribute, String newValue) {
        String SQL = "UPDATE sep5.Applicant SET " + attribute + " = '" + newValue + "' WHERE username = '" + name + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Get an ApplicantProfile from the DB
    public Applicant getApplicantProfile(String username){
        String SQL = "SELECT* FROM sep5.Applicant WHERE username = ' " + username + "';";
        ResultSet rs;
        Applicant result = new Applicant(username);
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
//            result.setFullName(rs.getString(1));
//            result.setProfileInformation(rs.getString(2));
//            result.setContact(rs.getString(3));
//            result.setEducation(rs.getString(4));
//            result.setLanguages(rs.getString(5));
//            result.setExperience(rs.getString(6));
//            result.setSkills(rs.getString(7));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }


//-----------------------------------------------------------------------------------------------------------Company OPERATIONS
    // Stores a new Company in the DB
    public void insertNewCompany(Company company) {
        insertNewUser(company);
//        String SQL = "INSERT INTO sep5.company VALUES "
//                + "(DEFAULT, '" + company.getUsername() + "', '" + company.getCompanyName() +"', '"
//                + company.getDescription()+"');";
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeQuery(SQL);
//
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    //Get a CompanyProfile from the DB
    public Company getCompanyProfile(String username){
        String SQL = "SELECT* FROM sep5.Company WHERE username = ' " + username + "';";
        ResultSet rs;
        Company result = new Company(username);
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
//            result.setCompanyName(rs.getString(1));
//            result.setDescription(rs.getString(2));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }



//-----------------------------------------------------------------------------------------------------------JobAdd OPERATIONS
    // Stores a new JobAd in the DB
    public void insertNewJobAdd(JobAdd jobAdd) {
        String SQL = "INSERT INTO sep5.jobad VALUES "
                + "(DEFAULT, '" + jobAdd.getJobTitle() + "', '" + jobAdd.getCompany() +"', '"
                + jobAdd.getJobDescription()+"', '"+jobAdd.getRequirements()+"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Updates the table JobAd in the DB
    public void updateJobAd(String name, String attribute, String newValue) {
        String SQL = "UPDATE sep5.jobad SET " + attribute + " = '" + newValue + "' WHERE jobtitle = '" + name + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void close() {
        // Close the connection
        try {
            connection.close();
            System.out.println("Connection closed");
        } catch (SQLException exception) {
            System.out.println("Connection closing failed");
            exception.printStackTrace();
        }
    }


}