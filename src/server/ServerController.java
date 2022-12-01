package server;

import common.Applicant;
import common.Company;
import common.IServerConnector;
import common.User;

import java.util.ArrayList;

public class ServerController implements IServerConnector {

    JDBCConnector database = new JDBCConnector();
    //database.connect("hattie.db.elephantsql.com", 5432, "zdpvllpz", "DkaoNfKGKMNfkg8bVfKyN3pJxPM2GWmn");

    private ArrayList<User> users;
    private ArrayList<Applicant> applicants;
    private ArrayList<Company> companys;

    public static void start(){

    }

    @Override
    public char getUserType() {
        return 0;
    }
}
