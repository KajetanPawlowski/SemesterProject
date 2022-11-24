package client.model;

import common.Company;
import common.JobAdd;

import java.util.ArrayList;

public class UserModel implements IUserModel {
    private UserModelState currentUserState;
    private ArrayList<JobAdd> jobAdds;


    private static UserModel currentInstance = null;
    private UserModel(){
        jobAdds = new ArrayList<JobAdd>();
        setJobAdds(20);
    }

    public static UserModel getInstance(){
        if(currentInstance == null){
            return new UserModel();
        }
        return currentInstance;
    }

    public void setCurrentUserState() throws UserNotFoundException{
        char userType = 'A'; //server.getUserType(String username) // 0 for empty
        if(userType == 0){
            throw new UserNotFoundException();
        }
        if(userType =='A'){
            currentUserState = new ApplicantState();
        }else{
            currentUserState = new CompanyState();
        }
    }



    public void setJobAdds(int noOfJobs){
        for (int i = 0; i<noOfJobs; i++){
            String jobD = i + "\nLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
            String jobR = i + "\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the ";
            jobAdds.add(new JobAdd("Job Add " + i, new Company("Company "+ i),"Subtitle "+i,jobD,jobR));
        }
    }

    public ArrayList<JobAdd> getJobAdds(){
        return jobAdds;
    }
}
