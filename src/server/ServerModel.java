package server;

import common.Applicant;
import common.Company;
import common.User;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class ServerModel {
    private ArrayList<User> users;
    private ArrayList<Applicant> applicants;
    private ArrayList<Company> companies;

    public ServerModel(){

    }

    public static void run(){
        try {
            LocateRegistry.createRegistry( 1099 );

            ServerController controller = new ServerController();

            Naming.rebind( "server", controller );

            System.out.println( "Server listening on " + InetAddress.getLocalHost().getHostAddress() );
        } catch( Exception ex ) {
            ex.printStackTrace();
        }
    }
}
