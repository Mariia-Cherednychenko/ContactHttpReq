package dto_user;

import lombok.Data;

import java.util.List;


@Data
public class UserResponse {
    private String status; // должно быть ок
    private List<User> users;
    private String error;  // должно быть 0

}
