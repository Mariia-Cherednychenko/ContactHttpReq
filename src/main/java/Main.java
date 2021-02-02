import com.fasterxml.jackson.databind.ObjectMapper;
import dto_server.ServerRequest;
import dto_server.ServerResponse;
import dto_user.User;
import menuAction.*;
import usersService.UsersService;

import java.net.http.HttpClient;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        ObjectMapper objectMapper = new ObjectMapper();
        Scanner scanner = new Scanner(System.in);

        User user = new User();
        UsersService usersService=new UsersService(httpClient,objectMapper);

        String linkRegister = "https://mag-contacts-api.herokuapp.com/register";
        String linkLogin = "https://mag-contacts-api.herokuapp.com/login";
        String linkContacts = "https://mag-contacts-api.herokuapp.com/contacts";
        String linkAddContacts ="https://mag-contacts-api.herokuapp.com/contacts/add";
        String linkFindContact="https://mag-contacts-api.herokuapp.com/contacts/find";
        String linkUsers = "https://mag-contacts-api.herokuapp.com/users";


        ServerResponse serverResponse = new ServerResponse();
        ServerRequest serverRequest = new ServerRequest();


        Menu menu = new Menu(scanner,serverResponse);
        menu.addAction(new LoginMenuAction(usersService,scanner,linkLogin, serverResponse, serverRequest));
        menu.addAction(new RegisterMenuAction(usersService,scanner,linkRegister, serverResponse, serverRequest, user));
        menu.addAction(new ShowAllContactsMenuAction(usersService,scanner,linkContacts, serverResponse, serverRequest));
        menu.addAction(new SearchByNameMenuAction(usersService,scanner,linkFindContact, serverResponse, serverRequest));
        menu.addAction(new SearchByContactMenuAction(usersService,scanner,linkFindContact, serverResponse, serverRequest));
        menu.addAction(new AddContactMenuAction(usersService,scanner,linkAddContacts, serverResponse, serverRequest));
        menu.addAction(new ShowAllUsersMenuAction(usersService,scanner,linkUsers, serverResponse, serverRequest));
        menu.addAction(new ExitMenuAction(serverResponse));
        menu.run();

    }
}
