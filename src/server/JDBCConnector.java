package server;

import client.model.UserNotFoundException;
import common.transferObjects.*;
import common.util.LogBook;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JDBCConnector implements IJDBCConnector{
    private Connection connection;

    @Override
    public void connect(String host, int portNo, String userName, String password) throws ConnectionFailedException{
        // Establishing a PostgreSQL database connection
        String databaseUrl = "jdbc:postgresql://" + host + ":" + portNo + "/" + userName;

        try {
            connection = DriverManager.getConnection(databaseUrl, userName, password);
            LogBook.getInstance().quickDBLog("connect::success::" + databaseUrl);
        } catch (PSQLException exception) {
            LogBook.getInstance().quickDBLog("connect::failed");
            throw new ConnectionFailedException();
        } catch (Exception ex) {
            LogBook.getInstance().quickDBLog("connect::exception::"+ ex.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void close() {
        // Close the connection
        try {
            connection.close();
            LogBook.getInstance().quickDBLog("close::success");
        } catch (SQLException exception) {
            LogBook.getInstance().quickDBLog("close::fail");
            exception.printStackTrace();
        }
    }

    @Override
    public void insertNewUser(User user){
        String SQL = "INSERT INTO sep5.User VALUES "
                + "('" + user.getUsername() + "', '" + user.getType() +"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewUser::"+ex.getMessage());
        }
        if(user.getType() == 'A'){
            insertNewApplicant((Applicant) user);
        }else{
            insertNewCompany((Company) user);
        }
    }

    private void insertNewCompany(Company company) {
//        String SQL = "INSERT INTO sep5.company VALUES "
//                + "(DEFAULT, '" + company.getUsername() + "', '" + company.getFullName() + "', '"
//                + company.getDetails() + "');";
        String SQL = "INSERT INTO sep5.company VALUES "
                + "('" + company.getUsername() + "', NULL, NULL, NULL, NULL, NULL);";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewCompany::" + ex.getMessage());
        }
    }

    private void insertNewApplicant(Applicant applicant) {
//        String SQL = "INSERT INTO sep5.applicant VALUES "
//                + "('" + applicant.getUsername() + "', '" + applicant.getFullName() + "', '" + applicant.getSubtitle()  + "', '"
//                + applicant.getDetails() + "', ARRAY " + applicant.getQualities().toString()  + ", ARRAY [" + applicant.getConvsId() +"]);";
        String SQL = "INSERT INTO sep5.applicant VALUES "
                + "('" + applicant.getUsername() + "', NULL, NULL, NULL, NULL, NULL);";

        System.out.println(SQL);
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewApplicant::" + ex.getMessage());
        }
    }

    @Override
    public void insertNewJobAdd(JobAd jobAd) {
        String SQL = "INSERT INTO sep5.jobad VALUES "
                + "(DEFAULT, '" + jobAd.getJobTitle() + "', '" + jobAd.getCompany() +"', '"
                + jobAd.getJobDescription()+"', '"+jobAd.getRequirements()+"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewJobAd::" + ex.getMessage());
        }
    }

    @Override
    public void insertQuality(String quality) {
        String SQL = "INSERT INTO sep5.qualities VALUES " + "('"+ quality+"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewQuality::" + ex.getMessage());
        }

    }

    @Override
    public int insertConversation(Conversation conversation) {
        String SQL = "INSERT INTO sep5.converastion VALUES "
                + "(DEFAULT, '" + Arrays.toString(conversation.getUsers()) + "', '" + conversation.getJobId() +"', '"
                + conversation.getMessages()+"');";
        ResultSet rs;
        int convId =0;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
            convId = rs.getInt(1);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewConversation::" + ex.getMessage());
        }

        return convId;
    }

    @Override
    public User getUser(String username) throws UserNotFoundException{
        User user;
        if(getUserType(username) =='A'){
            user = getApplicantProfile(username);
        }else{
            user = getCompanyProfile(username);
        }
        return  user;

    }
    private char getUserType(String username) throws UserNotFoundException{
        String SQL = "SELECT type FROM sep5.User WHERE username = '"+username+"';";
        ResultSet rs;
        char userType = 0;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
            userType =  rs.getString(1).charAt(0);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getUserType::"+ex.getMessage());
        }
        if(userType == 0){
            throw new UserNotFoundException();
        }
        return userType;
    }

    //Get a CompanyProfile from the DB
    private Company getCompanyProfile(String username){
        String SQL = "SELECT* FROM sep5.Company WHERE username = '" + username + "';";
        ResultSet rs;
        Company company = new Company(username);
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
            company.setFullName(rs.getString(1));
            company.setDetails(rs.getString(2));
            Array temp = rs.getArray(3);
            List list =Arrays.asList(temp);
            company.setConvs(getConversationsList(new ArrayList<Integer>(list)));

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getCompanyProfile::"+ex.getMessage());
        }
        return company;
    }

    // Get an ApplicantProfile from the DB
    private Applicant getApplicantProfile(String username){
        String SQL = "SELECT* FROM sep5.applicant WHERE username = '" + username + "';";
        System.out.println(SQL);
        ResultSet rs;
        Applicant applicant = new Applicant(username);
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
            applicant.setFullName(rs.getString("fullname"));
            applicant.setSubtitle(rs.getString("subtitle"));
            applicant.setDetails(rs.getString("personalinformation"));
//            Array temp = rs.getArray(4);
//            List list = Arrays.asList(temp);
//            applicant.setQualities(new ArrayList<String>(list));
//            temp = rs.getArray(5);
//            list = Arrays.asList(temp);
//            applicant.setConvs(getConversationsList(new ArrayList<Integer>(list)));

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getApplicantProfile::"+ex.getMessage());
        }
        return applicant;
    }

    private ArrayList<Conversation> getConversationsList(ArrayList<Integer> convIds){
        ArrayList conversations = new ArrayList<Conversation>();
        for(Integer convId : convIds ){
            conversations.add(getConversation(convId));
        }
        return conversations;
    }

    private Conversation getConversation(Integer id){
        /// SQL CONVESATION WHERE ID = ID

        //Conversation result......
        return new Conversation(id);
    }

    @Override
    public ArrayList<String> getAllQualities (){
        String SQL = "SELECT* FROM sep5.qualities ;";
        ResultSet rs;
        ArrayList<String> allQualities = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            while(rs.next()){
                allQualities.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getAllQualities::"+ex.getMessage());
        }
        return allQualities;
    }


    // Get JobAd ID from the DB
    private int getJobAdId(String jobTitle){
        String SQL = "SELECT jobId FROM sep5.jobAd WHERE jobTitle = '" + jobTitle + "';";
        ResultSet rs;
        int result = 0;
        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
            result = rs.getInt(1);

        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<JobAd> getAllJobAds() {

      ArrayList<JobAd> allJobAds = new ArrayList<>();
//        int jobId = getJobAdId();
//
//        allJobAds.add(getJobAd(jobId));
        // I AM STUCK WITH THIS ONE; tried
//
       return allJobAds;
    }
}


//    //-----------------------------------------------------------------------------------------------------------User OPERATIONS
//    // Stores a new User in the DB

//    // Gets the user type from the DB

//
//
//    //-----------------------------------------------------------------------------------------------------------Applicant OPERATIONS

//
//    // Updates the table Applicant in the DB
//    public void updateApplicant(String name, String attribute, String newValue) {
//        String SQL = "UPDATE sep5.Applicant SET " + attribute + " = '" + newValue + "' WHERE username = '" + name + "';";
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeQuery(SQL);
//
//        } catch (SQLException ex) {
//            LogBook.getInstance().quickDBLog("updateApplicant::"+ex.getMessage());
//        }
//    }
//

//



//
//

//
//
//    //-----------------------------------------------------------------------------------------------------------Company OPERATIONS
//    // Stores a new Company in the DB

//

//
//
//
//    //-----------------------------------------------------------------------------------------------------------JobAdd OPERATIONS
//    // Get JobAd ID from the DB
//    public int getJobAdId(String jobTitle){
//        String SQL = "SELECT jobId FROM sep5.jobAd WHERE jobTitle = '" + jobTitle + "';";
//        ResultSet rs;
//        int result = 0;
//        try{
//            Statement statement = connection.createStatement();
//            rs = statement.executeQuery(SQL);
//
//            rs.next();
//            result = rs.getInt(0);
//
//        }catch(SQLException ex){
//            System.out.println(ex.getMessage());
//        }
//        return result;
//    }
//
//    // Stores a new JobAd in the DB
//    public void insertNewJobAdd(JobAdd jobAdd) {

//    }
//
//    // Updates the table JobAd in the DB
//    public void updateJobAd(String name, String attribute, String newValue) {
//        String SQL = "UPDATE sep5.jobad SET " + attribute + " = '" + newValue + "' WHERE jobtitle = '" + name + "';";
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeQuery(SQL);
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }