package menuAction;

import dto_contact.Contact;
import dto_server.ServerRequest;
import dto_server.ServerResponse;
import lombok.AllArgsConstructor;
import usersService.UsersService;

import java.util.Scanner;

@AllArgsConstructor
public class AddContactMenuAction implements MenuAction{

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
        return "добавить контакт ";
    }

    @Override
    public void execute() {
        System.out.print ("Введите имя контакта: ");
        String name = scanner.nextLine();

        /*System.out.print ("Введите тип контакта: ");
        String type = scanner.nextLine();

        System.out.print ("Введите контакт: ");
        String contact = scanner.nextLine();
       usersService.addContact(serverResponse.getToken(),link, new Contact(name,contact,type));*/

        while(true) {

            System.out.print ("Введите контакт: ");
            String value = scanner.nextLine();

            if (!usersService.addContact(serverResponse.getToken(),link, new Contact(name,value))) {
                System.out.println("Неправильный ввод");
                continue;
            }
            break;
        }
    }
}
