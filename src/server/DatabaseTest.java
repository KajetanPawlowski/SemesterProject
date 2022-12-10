package server;

import common.transferObjects.Conversation;

import java.util.Arrays;

public class DatabaseTest {
    static JDBCConnector database = new JDBCConnector();

    public static void main(String[] args) {
        database.connect("hattie.db.elephantsql.com", 5432, "zdpvllpz", "DkaoNfKGKMNfkg8bVfKyN3pJxPM2GWmn");
        System.out.println(database.getAllQualities().toString());
        Conversation conversation = new Conversation(1);

        conversation
        Arrays.toString(conversation.getUsers());


    }
}
