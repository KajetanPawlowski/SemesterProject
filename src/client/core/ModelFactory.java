package client.core;


import client.model.ClientChatModel;
import client.model.IClientModel;
import client.model.Model;

public class ModelFactory {
    private IClientModel clientModel;
    private ClientChatModel clientChatModel;

    public Model getClientModel(){
        if(clientModel == null){
            clientModel = new Model();
        }
        return (Model)clientModel;
    }

    public IClientModel getClientChatModel(){
        if(clientChatModel == null){
            clientChatModel = new ClientChatModel() {
            };
        }
        return clientChatModel;
    }


}
