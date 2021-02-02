package usersService;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto_contact.Contact;
import dto_contact.ContactsResponse;
import dto_contact.GeneralContact;
import dto_server.ServerRequest;
import dto_server.ServerResponse;
import dto_user.User;
import dto_user.UserResponse;
import exception.ServerException;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


@RequiredArgsConstructor
public class UsersService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    ServerResponse serverResponse;
    ServerRequest serverRequest;
    User user;


    public ServerResponse register(User user, String link) {
        this.user = user;

        try {
            String userRegisterRequest = objectMapper.writeValueAsString(this.user);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(link))
                    .POST(HttpRequest.BodyPublishers.ofString(userRegisterRequest))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();

            return getServerResponse(request);

        } catch (Exception e) {
            new ServerException("Ошибка при регестрации").getMessage();
        }
        return null;
    }

    public ServerResponse login(ServerRequest serverRequest, String link) {
        this.serverRequest = serverRequest;

        try {
            String userLoginRequest = objectMapper.writeValueAsString(this.serverRequest);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(link))
                    .POST(HttpRequest.BodyPublishers.ofString(userLoginRequest))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();

            return getServerResponse(request);

        } catch (Exception e) {
            new ServerException("Ошибка при входе").getMessage();
        }
        return null;
    }

    public List<Contact> getAllContacts(String token, String link) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            ContactsResponse contactsResponse = objectMapper.readValue(response.body(), ContactsResponse.class);

            return contactsResponse.getContacts();

        } catch (Exception e) {
            new ServerException("Ошибка при получении всех контактов").getMessage();
        }
        return null;
    }

    public List<User> getAllUsers(String token, String link) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            UserResponse contactsResponse = objectMapper.readValue(response.body(), UserResponse.class);

            return contactsResponse.getUsers();

        } catch (Exception e) {
            new ServerException("Ошибка при получении всех пользователей").getMessage();
        }
        return null;
    }

    public List<Contact> searchContact(String token, String link, GeneralContact partOfContact) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .POST(HttpRequest.BodyPublishers.ofString(String.valueOf(partOfContact)))
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            ContactsResponse contactsResponse = objectMapper.readValue(response.body(), ContactsResponse.class);

            return contactsResponse.getContacts();
        } catch (Exception e) {
            new ServerException("Ошибка при получении всех контактов").getMessage();
        }

        return null;
    }

    public boolean addContact(String token, String link, Contact contact) {

        try {
            String addContactRequest = objectMapper.writeValueAsString(contact);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(link))
                    .POST(HttpRequest.BodyPublishers.ofString(addContactRequest))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());

            return true;

        } catch (Exception e) {
            new ServerException("Ошибка при добавлении контакта").getMessage();
        }
        return false;

    }

    private ServerResponse getServerResponse(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return serverResponse = objectMapper.readValue(response.body(), ServerResponse.class);
    }
}
