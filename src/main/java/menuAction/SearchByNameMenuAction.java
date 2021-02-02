package menuAction;

import dto_contact.Contact;
import dto_contact.ContactSearchByName;
import dto_server.ServerRequest;
import dto_server.ServerResponse;
import lombok.AllArgsConstructor;
import usersService.UsersService;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class SearchByNameMenuAction implements MenuAction{

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
        return "поиск по имени";
    }

    @Override
    public void execute() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        List<Contact> contactList = (List<Contact>) usersService.searchContact(serverResponse.getToken(),link, new ContactSearchByName(name));
        System.out.println(contactList);
    }
}
