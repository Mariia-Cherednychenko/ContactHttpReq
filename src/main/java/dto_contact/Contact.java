package dto_contact;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact implements GeneralContact {

    private String name;
    private String value;
    /*private String type;

    @Override
    public String toString() {
        return "------------" +
                "тип: " + type +
                "имя:" + name + '\'' +
                "контакт: " + value +
                "------------";
    }*/


    private Type type;


    public enum Type {
        PHONE,
        EMAIL
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value){
        setContactAndType(value);
    }

    public void setType(String type){
        if(type==Type.PHONE.toString())
        this.type= Type.PHONE;

        else if(type==Type.EMAIL.toString())
            this.type=Type.EMAIL;

        else System.out.println("Неправилный ввод");

    }

    public Contact(String name, String contact) {
        setName(name);
        setContactAndType(contact);
    }


   private void setContactAndType(String contact) {
        checkAndFillIfContactIsEmail(contact);

        checkAndFillIfContactIsPhone(contact);

        if (this.value == null) System.out.println("Неверный формат ввода контакта");
    }

    private void checkAndFillIfContactIsPhone(String contact) {
        String phoneShow = contact.replaceAll("\\D", "");
        Pattern patternPhone1 = Pattern.compile("380\\d{9}");
        Pattern patternPhone2 = Pattern.compile("80\\d{9}");
        Pattern patternPhone3 = Pattern.compile("0\\d{9}");

        if (patternPhone1.matcher(phoneShow).matches()) {
            this.value = "+" + phoneShow.substring(0, 2) + " " + phoneShow.substring(2, 5) + " " +
                    phoneShow.substring(5, 8) + " " + phoneShow.substring(8);
            this.type = Type.PHONE;
        } else if (patternPhone2.matcher(phoneShow).matches()) {
            this.value = "+3" + phoneShow.charAt(0) + " " + phoneShow.substring(1, 4) + " " +
                    phoneShow.substring(4, 7) + " " + phoneShow.substring(7);
            this.type = Type.PHONE;
        } else if (patternPhone3.matcher(phoneShow).matches()) {
            this.value = "+38 " + phoneShow.substring(0, 3) + " " +
                    phoneShow.substring(3, 6) + " " + phoneShow.substring(6);
            this.type = Type.PHONE;
        }
    }

    private void checkAndFillIfContactIsEmail(String contact) {
        Pattern patternEmail = Pattern.compile("(\\w+)@(\\w+).([A-Za-z]+)");
        if (patternEmail.matcher(contact).matches()) {
            this.value = contact;
            this.type = Type.EMAIL;
        }
    }

    @Override
    public String toString() {
        return "------------" +
                "тип: " + type +
                "имя:" + name + '\'' +
                "контакт: " + value +
                "------------";
    }


}
