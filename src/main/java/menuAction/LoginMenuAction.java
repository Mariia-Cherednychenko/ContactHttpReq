package menuAction;

import dto_server.ServerRequest;
import dto_server.ServerResponse;
import lombok.AllArgsConstructor;
import usersService.UsersService;

import java.util.Scanner;

@AllArgsConstructor
public class LoginMenuAction  implements MenuAction{

    UsersService usersService;
    Scanner scanner;
    String link;
    ServerResponse serverResponse;
    ServerRequest serverRequest;


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
        return "войти";
    }

    @Override
    public void execute() {
        System.out.print("\nВведитe логин: ");
        serverRequest.setLogin(scanner.nextLine());
        System.out.print("Введитe пароль: ");
        serverRequest.setPassword(scanner.nextLine());

        serverResponse = usersService.login(serverRequest, link);

        if(serverResponse.getToken()==null) System.out.println("Вход не выполнен");
        else System.out.println("\nВход выполнен успешно");
    }
}
