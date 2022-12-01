package server;

import client.ClientApp;
import javafx.application.Application;

public class RunFirst {

    public static void main(String[] args) {
        JDBCConnector database = new JDBCConnector();
        database.connect("hattie.db.elephantsql.com", 5432, "zdpvllpz", "DkaoNfKGKMNfkg8bVfKyN3pJxPM2GWmn");
        try{
            System.out.println("Usertype:: " + database.getUserType("k.pawloski"));
        }catch (RuntimeException ex){
            System.out.println("UserNotFoundEx");
        }

    }
}
