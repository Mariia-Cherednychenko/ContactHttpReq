import menuAction.MenuAction;
import dto_server.ServerResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {


    private MenuAction[] actions;
    Scanner scanner;
    ServerResponse serverResponse;
    private ArrayList<MenuAction> userMenu;

    public Menu(Scanner scanner, ServerResponse serverResponse) {
        this.actions = new MenuAction[0];
        this.scanner = scanner;
        this.serverResponse = serverResponse;
        this. userMenu = new ArrayList<>();
    }

    public void addAction(MenuAction action) {
        actions = Arrays.copyOf(actions, actions.length + 1);
        actions[actions.length - 1] = action;
    }

    public void run() {
        System.out.println("\nПрограмма запущена\n" +
                           "--------------------\n");
        while (true) {
            showUserMenu();
            int choice;
            try {choice = getMenuIndexFromUser();}
            catch (Exception E) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
                continue;
            }
            if (!validateMenuIndex(choice)) {
                System.out.println("Неверный ввод");
                continue;
            }
            userMenu.get(choice).execute();
            if (userMenu.get(choice).closeAfter()) break;

        }
    }

    private boolean validateMenuIndex(int choice) {
        return choice >= 0 && choice < userMenu.size();
    }

    private int getMenuIndexFromUser() {
        System.out.print("\nВведите Ваш выбор:  ");
        int ch = scanner.nextInt();
        scanner.nextLine();
        return ch - 1;
    }

    private void showUserMenu() {
        int index=0;
        userMenu.clear();
        System.out.println("---------------------");
            for (int i = 0; i < actions.length; i++) {
               String token =  actions[i].getToken();
               if(token!=null&& (i+1)<actions.length)actions[i+1].setToken(token);
               if(actions[i].isVisible()) {
                    userMenu.add(actions[i]);
                    System.out.printf("%2d - %s\n", index + 1, userMenu.get(index).getName());
                    index = index+1;
                }
            }
        System.out.println("---------------------");

    }
}
