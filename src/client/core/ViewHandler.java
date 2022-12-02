package client.core;


import client.view.applicant.ApplicantController;
import common.LogBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private LogBook log = LogBook.getInstance();
    private ViewModelFactory vmf;
    private Scene mainScene;
    private Stage stage;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
        stage = new Stage();
    }

    public void start() {
        openLoginView();
    }

    public void openLoginView() {
        log.printActivities();
            BorderPane root = (BorderPane)createFromFXML(vmf.getLoginVM(),"../view/login/LoginView.fxml");

            //No MENU BAR

            mainScene = new Scene(root,800, 600);
            stage.setScene(mainScene);
            stage.setTitle("Login");
            stage.show();

    }
    public void openEditApplicantProfileView(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getEditApplicantVM(),"../view/editApplicantProfile/EditApplicantProfile.fxml");
        root.setLeft(getMenuBar());
        BorderPane center = (BorderPane)root.getCenter();
        center.setCenter(getApplicantCV());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Edit Profile");
        stage.show();

    }

    public void openOverview() {
            BorderPane root = (BorderPane)createFromFXML(vmf.getOverviewVM(),"../view/overview/Overview.fxml");

            root.setLeft(getMenuBar());

            mainScene = new Scene(root,800, 600);
            stage.setScene(mainScene);
            stage.setTitle("OverView");
            stage.show();
    }

    public void openJobSearch(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getJobSearchVM(),"../view/jobSearch/JobSearch.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Job Search");
        stage.show();
    }
    public void openChatView(){
        BorderPane root = (BorderPane)createFromFXML(vmf.getChatVM(),"../view/chat/ChatView.fxml");

        root.setLeft(getMenuBar());

        mainScene = new Scene(root,800, 600);
        stage.setScene(mainScene);
        stage.setTitle("Chat");
        stage.show();
    }
    public VBox getMenuBar(){
        if(vmf.getModel().getUsertype()=='A'){
            return getApplicantMenuBar();
        }else{
            return getCompanyMenuBar();
        }
    }

    private VBox getApplicantMenuBar(){
        return(VBox)(createFromFXML(vmf.getMenuVM(), "../view/menu/Menu.fxml"));
    }
    private VBox getCompanyMenuBar(){
        return(VBox)(createFromFXML(vmf.getMenuCompanyVM(), "../view/menuCompany/MenuCompany.fxml"));
    }
    public VBox getApplicantCV(){
        return (VBox)(createFromFXML(vmf.getApplicantVM(), "../view/applicant/Applicant.fxml"));
    }

    private Parent createFromFXML(ViewModel vm, String filePath){
        Parent layout;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(filePath));
            layout = loader.load();

            FXMLController controller = loader.getController();
            controller.init(this, vm);


        } catch (IOException e) {
            e.printStackTrace();
            layout = null;
        }
        return layout;
    }

    public Stage getStage(){
        return stage;
    }


}
