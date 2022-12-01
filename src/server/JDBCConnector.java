package server;

import common.Applicant;
import common.Company;
import common.JobAdd;
import org.postgresql.util.PSQLException;

import java.sql.*;

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


//-----------------------------------------------------------------------------------------------------------Applicant OPERATIONS
    // Stores a new JobAd in the DB
    private void insertNewApplicant(Applicant applicant) {
        String SQL = "INSERT INTO sep5.jobad VALUES "
                + "(DEFAULT, '" + applicant.getUsername() + "','" + applicant.getFullName() + "', '" + applicant.getPersonalInformation() +"', '"
                + applicant.getContact()+"', '"+applicant.getEducation()+"' , '"+applicant.getLanguages()+"' , '"
                +applicant.getExperience()+"' , '"+applicant.getSkills()+"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

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


//-----------------------------------------------------------------------------------------------------------Company OPERATIONS
    // Stores a new Compnay in the DB
    private void insertNewCompany(Company company) {
        String SQL = "INSERT INTO sep5.company VALUES "
                + "(DEFAULT, '" + company.getUsername() + "', '" + company.getCompanyName() +"', '"
                + company.getDescription()+"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}



//-----------------------------------------------------------------------------------------------------------JobAdd OPERATIONS
    // Stores a new JobAd in the DB
    private void insertNewJobAdd(JobAdd jobAdd) {
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