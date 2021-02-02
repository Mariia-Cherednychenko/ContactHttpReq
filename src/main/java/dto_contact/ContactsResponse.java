package dto_contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactsResponse implements GeneralContact {
    private String status; // должно быть ок
    private List<Contact> contacts;
    private String error;  // должно быть 0
}
