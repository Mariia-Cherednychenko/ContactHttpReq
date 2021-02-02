package menuAction;

import dto_contact.Contact;
import dto_contact.ContactSearchByValue;
import dto_server.ServerRequest;
import dto_server.ServerResponse;
import lombok.AllArgsConstructor;
import usersService.UsersService;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class SearchByContactMenuAction implements MenuAction{

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
        return "поиск по контакту";
    }

    @Override
    public void execute() {
        System.out.print("Введите контакт: ");
        String contact = scanner.nextLine();
        List<Contact> contactList = (List<Contact>) usersService.searchContact(serverResponse.getToken(),link,new ContactSearchByValue(contact));
        System.out.println(contactList);
    }
}
