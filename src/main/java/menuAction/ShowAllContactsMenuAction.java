package menuAction;

import dto_contact.Contact;
import dto_server.ServerRequest;
import dto_server.ServerResponse;
import lombok.AllArgsConstructor;
import usersService.UsersService;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ShowAllContactsMenuAction implements MenuAction{

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
        return "посмотреть контакты";
    }

    @Override
    public void execute() {
        List<Contact> contactList = usersService.getAllContacts(serverResponse.getToken(),link);
        System.out.println(contactList);
    }
}
