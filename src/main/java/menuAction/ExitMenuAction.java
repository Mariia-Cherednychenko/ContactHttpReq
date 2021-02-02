package menuAction;


import dto_server.ServerResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExitMenuAction implements MenuAction {

    ServerResponse serverResponse;

    @Override
    public String getToken(){
        return  serverResponse.getToken();}
    @Override
    public void setToken(String token){
        serverResponse.setToken(token);}

    @Override
    public boolean isVisible(){
        return  (serverResponse.getToken()==null ? true : true);
    }

    @Override
    public String getName() {
        return "выход";
    }

    @Override
    public void execute() {
        System.out.println("Программа закрывается");
    }

    @Override
    public boolean closeAfter() {
        return true;
    }
}
