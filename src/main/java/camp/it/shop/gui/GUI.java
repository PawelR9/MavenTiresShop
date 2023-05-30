package camp.it.shop.gui;

import camp.it.shop.core.ICore;
import camp.it.shop.model.UserRoles;
import camp.it.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GUI implements IGUI {

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public ICore core;

    @Override
    public String logOrReg() {
        System.out.println("1. Log in");
        System.out.println("2. Register");
        System.out.println("3. Exit");

        return scanner.nextLine();
    }

    @Override
    public String showMenu() {
        System.out.println("1. View product list");
        System.out.println("2. Buy the product");
        System.out.println("3. Logout");
        if (core.getSession() != null && core.getSession().getLoggedInUser().getRole().equals(UserRoles.ADMIN)) {
            System.out.println("4. Add products");
            System.out.println("5. Change user role");
        }
        return scanner.nextLine();
    }

    @Override
    public User readLoginAndPassword() {
        System.out.println("Login:");
        String login = scanner.nextLine().toLowerCase();
        System.out.println("Password:");
        String password = scanner.nextLine();
        return new User(login, password, UserRoles.USER);
    }
}
