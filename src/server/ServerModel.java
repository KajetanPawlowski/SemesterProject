package server;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerModel {
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
