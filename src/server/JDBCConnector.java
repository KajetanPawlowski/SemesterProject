package server;

import client.model.UserNotFoundException;
import common.transferObjects.*;
import common.util.LogBook;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;


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
        String SQL = "INSERT INTO sep5.company VALUES "
                + "('" + company.getUsername() + "', '" + company.getFullName() + "', '"
                + company.getDetails() + "', " + company.getConvsIdForDB() +");";

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewCompany::" + ex.getMessage());
        }
    }

    private void insertNewApplicant(Applicant applicant) {
        String SQL = "INSERT INTO sep5.applicant VALUES "
                + "('" + applicant.getUsername() + "', '" + applicant.getFullName() + "', '" + applicant.getSubtitle()  + "', '"
                + applicant.getDetails() + "', " + applicant.getQualitiesForDB() + ", " + applicant.getConvsIdForDB() +");";
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
                + "(DEFAULT, '" + jobAd.getJobTitle() + "', '" + jobAd.getCompany().getUsername() +"', '"
                + jobAd.getJobDescription()+"', " + jobAd.getRequirementsForDB() +");";
        System.out.println(SQL);
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
    public int insertConversation(User applicant, JobAd jobAd) {
        String SQL = "INSERT INTO sep5.conversation VALUES "
                + "(DEFAULT, ARRAY [ '" + applicant.getUsername()+ "' , '" + jobAd.getCompany().getUsername() +"'], '" + jobAd.getJobId()+ "');";

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("insertNewConversation::" + ex.getMessage());
        }

        ResultSet rs;
        int convId =0;
        String SQL2 = "SELECT converastionId FROM sep5.conversation " +
                "WHERE users = ARRAY [ '"  + applicant.getUsername() + "','"+ jobAd.getCompany().getUsername() + "'] ORDER BY converastionId DESC;";
        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL2);

            rs.next();
            convId = rs.getInt("converastionId");

        }catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getConversationId::" + ex.getMessage());
        }

        return convId;
    }

    @Override
    public void updateUser(User user) {
        if(user.getType() == 'A'){
            updateApplicant(user);
        }else{
            updateCompany(user);
        }
    }

    private void updateApplicant(User user) {
        String SQL = "UPDATE sep5.Applicant SET fullName = '" + user.getFullName() + "'," +
                " subtitle = '" + user.getSubtitle() + "'," +
                "personalinfromation = '" + user.getDetails() + "'," +
                "skills = " + user.getQualitiesForDB() + "," +
                "conversationsIds = " + user.getConvsIdForDB() + " WHERE username = '" + user.getUsername() + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("updateApplicant::"+ex.getMessage());
        }
    }

    private void updateCompany(User user) {
        String SQL = "UPDATE sep5.Company SET companyName = '" + user.getFullName() + "'," +
                "description = '" + user.getDetails() + "'," +
                "conversationsIds = " + user.getConvsIdForDB() + " WHERE username = '" + user.getUsername() + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("updateCompany::"+ex.getMessage());
        }
    }

    @Override
    public void updateJobAd(JobAd jobAd) {
        String SQL = "UPDATE sep5.jobAd SET jobTitle = '" + jobAd.getJobTitle() + "'," +
                "companyName = '" + jobAd.getCompany().getUsername() + "'," +
                "jobDescription = '" + jobAd.getJobDescription() + "'," +
                "requirements = " + jobAd.getRequirementsForDB() + ", " +
                "applicants = " + jobAd.getApplicantsForDB() +  " WHERE jobId = '" + jobAd.getJobId() + "';";
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("updateJobAd::"+ ex.getMessage());
        }
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
            company.setFullName(rs.getString("companyname"));
            company.setDetails(rs.getString("description"));

            Array conversations = rs.getArray(3);
            if(conversations != null){
                Integer[] int_conversations = (Integer[])conversations.getArray();
                company.setConvs(getConversationsList(int_conversations));
            }

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
            applicant.setDetails(rs.getString(4));

            applicant.setQualities(getQualitiesList(rs.getArray(5)));


            Array conversations = rs.getArray(6);
            if(conversations != null){
                Integer[] int_conversations = (Integer[])conversations.getArray();
                applicant.setConvs(getConversationsList(int_conversations));
            }


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getApplicantProfile::"+ex.getMessage());
        }
        return applicant;
    }
    private ArrayList<String> getQualitiesList(Array requirements) throws SQLException {
        if(requirements == null){
            return new ArrayList<String>();
        }
        String[] strQualities = (String[])requirements.getArray();
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < strQualities.length; i ++){
            result.add(strQualities[i]);
        }
        return result;
    }

    private ArrayList<Conversation> getConversationsList(Integer[] convIds){
        ArrayList conversations = new ArrayList<Conversation>();
        for(Integer convId : convIds ){
            conversations.add(getConversation(convId));
        }
        return conversations;
    }

    private ArrayList<User> getUsersList(String[] strUsers){
        ArrayList users = new ArrayList<User>();
        for(String username : strUsers){
            users.add(getUser(username));
        }
        return users;
    }

    private ArrayList<String> getMessageList(String[] strMessages){
        ArrayList messages = new ArrayList<String>();
        for(int i = 0; i< strMessages.length; i++){
            messages.add(strMessages[i]);
        }
        return messages;
    }
    //get all users method

    private Conversation getConversation(Integer id){
        String SQL = "SELECT* FROM sep5.conversation WHERE conversationId = '" + id + "';";
        ResultSet rs;
        Conversation conversation = new Conversation(id);
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

            rs.next();
            Array users = rs.getArray("users");
            if(users != null){
                String[] string_user = (String[])users.getArray();
                conversation.setUsers(getUsersList(string_user));
            }
            conversation.setJobId(rs.getInt("jobId"));

            Array messages = rs.getArray("messages");
            if(messages != null){
                String[] str_message = (String[])messages.getArray();
                conversation.setMessages(getMessageList(str_message));
            }


        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("getCompanyProfile::"+ex.getMessage());
        }
        return conversation;
    }

    @Override
    public ArrayList<Conversation> getConversations(ArrayList<Integer> convIds){
        ArrayList<Conversation> allConversations = new ArrayList<>();
        for(int i = 0; i < convIds.size(); i++){
            allConversations.add(getConversation(convIds.get(i)));
        }
        return allConversations;
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
        String SQL = "SELECT * FROM sep5.jobAd;";
        ResultSet rs;
        try{
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(SQL);
            while(rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString("jobTitle");
                Company company = getCompanyProfile(rs.getString("companyname"));
                String jobDescriptionJobAd = rs.getString("jobdescription");
                ArrayList<String> requirements = getQualitiesList(rs.getArray("requirements"));
                ArrayList<Applicant> applicants = getApplicants(getQualitiesList(rs.getArray("applicants")));
                allJobAds.add(new JobAd(id,title,company,jobDescriptionJobAd,requirements,applicants));
            }

        }catch(SQLException ex){
            LogBook.getInstance().quickDBLog("getAllJobAds::"+ex.getMessage());
        }

       return allJobAds;
    }
    private ArrayList<Applicant> getApplicants(ArrayList<String> usernames){
        ArrayList<Applicant> result = new ArrayList<>();
        for(int i = 0; i < usernames.size(); i++){
            result.add(getApplicantProfile(usernames.get(i)));
        }
        return result;
    }

    private void deleteUser(User user){
        String SQL ="";
        if(user.getType()== 'A'){
            SQL = "DELETE FROM sep5.applicant WHERE username = '" + user.getUsername() + "'; " +
                    "DELETE FROM sep5.user WHERE username = '"+ user.getUsername() + "'; ";
        }else{
            SQL = "DELETE FROM sep5.company WHERE username = '" + user.getUsername() + "'; " +
                    "DELETE FROM sep5.user WHERE username = '"+ user.getUsername() + "';";
        }

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);

        } catch (SQLException ex) {
            LogBook.getInstance().quickDBLog("deleteUser::" + ex.getMessage());
        }

    }

    @Override
    public void truncateAllTables() {
        String SQL = "TRUNCATE ONLY applicant CASCADE;\n" +
                "TRUNCATE ONLY company CASCADE;\n" +
                "TRUNCATE ONLY \"user\" CASCADE;\n" +
                "TRUNCATE ONLY jobAd RESTART IDENTITY CASCADE;\n" +
                "TRUNCATE ONLY conversation RESTART IDENTITY CASCADE;";

        try{
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        }catch(SQLException ex){
            LogBook.getInstance().quickDBLog("truncateTables::"+ex.getMessage());
        }
    }

    @Override
    public void truncateQualitiesTable(){
        String SQL = "TRUNCATE ONLY qualities CASCADE;";

        try{
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL);


        }catch(SQLException ex){
            LogBook.getInstance().quickDBLog("truncateQualitiesTable::"+ex.getMessage());
        }
    }


}




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
        // insert instead of update
//    }
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
