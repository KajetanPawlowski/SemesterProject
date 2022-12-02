package client.view.login;

public class InvalidLoginData extends RuntimeException{
    String msg = "";
    public InvalidLoginData(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}
