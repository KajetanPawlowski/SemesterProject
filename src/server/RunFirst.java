package server;

import client.ClientApp;
import javafx.application.Application;

public class RunFirst {

    public static void main(String[] args) {
	    Server.start();
        Application.launch(ClientApp.class);
    }
}