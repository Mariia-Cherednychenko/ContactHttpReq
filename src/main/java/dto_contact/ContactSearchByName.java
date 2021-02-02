package dto_contact;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactSearchByName implements GeneralContact {
    String name;

}
