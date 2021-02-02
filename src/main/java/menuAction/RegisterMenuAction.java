package menuAction;

import dto_server.ServerRequest;
import dto_server.ServerResponse;
import dto_user.User;
import lombok.AllArgsConstructor;
import usersService.UsersService;

import java.util.Scanner;

@AllArgsConstructor
public class RegisterMenuAction implements MenuAction{

    UsersService usersService;
    Scanner scanner;
    String link;
    ServerResponse serverResponse;
    ServerRequest serverRequest;
    User user;

    @Override
    public String getToken(){
        return  serverResponse.getToken();}
    @Override
    public void setToken(String token){
        serverResponse.setToken(token);}

    @Override
    public boolean isVisible(){
        return  (serverResponse.getToken()==null ? true : false);
    }


    @Override
    public String getName() {
        return "регистрация";
    }

    @Override
    public void execute() {
        System.out.print("Введитe логин:");
        user.setLogin(scanner.nextLine());
        serverRequest.setLogin(user.getLogin());

        System.out.print("Введитe пароль:");
        user.setPassword(scanner.nextLine());
        serverRequest.setPassword(user.getPassword());

        System.out.print("Введитe дату рождения:");
        user.setDateBorn(scanner.nextLine());

        serverResponse = usersService.register(user,link);
        System.out.println(serverResponse);
    }
}
