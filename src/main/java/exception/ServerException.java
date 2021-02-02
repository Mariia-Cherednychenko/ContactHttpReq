package exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ServerException extends Throwable {
    String message;


    @Override
    public String getMessage() {
        return String.format("Ошибка \n%s, \n%s, \n%s", message, super.getMessage(), super.getStackTrace());
    }
}
