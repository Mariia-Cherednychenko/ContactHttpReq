package menuAction;

import dto_server.ServerRequest;
import dto_server.ServerResponse;
import dto_user.User;
import lombok.AllArgsConstructor;
import usersService.UsersService;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ShowAllUsersMenuAction implements MenuAction{

    UsersService usersService;
    Scanner scanner;
    String link;
    ServerResponse serverResponse;
    ServerRequest serverRequest;

    @Override
    public boolean isVisible(){
        return  (serverResponse.getToken()==null ? false : true);
    }

    @Override
    public String getToken(){
        return  serverResponse.getToken();}
    @Override
    public void setToken(String token){
        serverResponse.setToken(token);}


    @Override
    public String getName() {
        return "посмотреть пользователей";
    }

    @Override
    public void execute() {
        List<User> userList = usersService.getAllUsers(serverResponse.getToken(),link);
        System.out.println(userList);
    }
}
