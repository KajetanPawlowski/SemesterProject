package server;

import common.Applicant;
import common.Company;
import common.User;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class ServerModel  {
    private JDBCConnector database;

    public ServerModel(JDBCConnector database){
        this.database = database;
    }


    private ArrayList<User> users;
    private ArrayList<Applicant> applicants;
    private ArrayList<Company> companies;


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

    public Applicant getApplicantProfile(Applicant user) {
        return database.getApplicantProfile(user.getUsername());
    }
}
