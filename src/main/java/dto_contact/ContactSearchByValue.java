package dto_contact;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class ContactSearchByValue implements GeneralContact {
    private String value;


    public ContactSearchByValue(String value) {
        checkAndFillIfContactIsPhone(value);
        if (this.value==null) checkAndFillIfContactIsEmail(value)  ;
    }

    private void checkAndFillIfContactIsPhone(String contact) {
        String phoneShow = contact.replaceAll("\\D", "");
        Pattern patternPhone1 = Pattern.compile("380\\d{9}");
        Pattern patternPhone2 = Pattern.compile("80\\d{9}");
        Pattern patternPhone3 = Pattern.compile("0\\d{9}");

        if (patternPhone1.matcher(phoneShow).matches()) {
            this.value = "+" + phoneShow.substring(0, 2) + " " + phoneShow.substring(2, 5) + " " +
                    phoneShow.substring(5, 8) + " " + phoneShow.substring(8);

        } else if (patternPhone2.matcher(phoneShow).matches()) {
            this.value = "+3" + phoneShow.charAt(0) + " " + phoneShow.substring(1, 4) + " " +
                    phoneShow.substring(4, 7) + " " + phoneShow.substring(7);

        } else if (patternPhone3.matcher(phoneShow).matches()) {
            this.value = "+38 " + phoneShow.substring(0, 3) + " " +
                    phoneShow.substring(3, 6) + " " + phoneShow.substring(6);

        }
    }

    private void checkAndFillIfContactIsEmail(String contact) {
        Pattern patternEmail = Pattern.compile("(\\w+)@(\\w+).([A-Za-z]+)");
        if (patternEmail.matcher(contact).matches()) {
            this.value = contact;
        }
    }

}
