package camp.it.shop.core;

import camp.it.shop.db.*;
import camp.it.shop.gui.IGUI;
import camp.it.shop.model.Tires;
import camp.it.shop.model.User;
import camp.it.shop.model.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;

@Component
public class Core implements ICore {

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    private IFileLoader fileLoader;
    @Autowired
    private IAuthenticator authenticator;
    @Autowired
    private ITiresRepository tiresRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IGUI gui;
    private Session session;

    public Core() {
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public void start() {
        try {
            fileLoader.readDataFromFile();
        } catch (IOException e) {
            System.out.println("Database is malformed!");
            return;
        }
        while (true) {

            switch (gui.logOrReg()) {

                case "1":
                    session = authenticator.authenticate();
                    if (session != null) {
                        menuAfterLogIn();
                    }
                    break;

                case "2":
                    registerNewUser();
                    break;

                case "3":
                    try {
                        fileLoader.saveDataToFile();
                        return;
                    } catch (IOException e) {
                        System.out.println("Database Error !!");
                    }
                    break;
                default:
                    System.out.println("Wrong choose!!");
                    break;
            }
        }
    }

    @Override
    public void menuAfterLogIn() {
        boolean exit = false;

        do {
            switch (gui.showMenu()) {
                case "1":
                    String role = session.getLoggedInUser().getRole();
                    Collection<Tires> tiresList = tiresRepository.getTires(role);
                    tiresRepository.listTires(tiresList, session);
                    break;
                case "2":
                    tiresRepository.sellTires(scanner);
                    break;
                case "3":
                    exit = true;
                    session = null;
                    break;
                case "4":
                    if (!session.getLoggedInUser().getRole().equals(UserRoles.ADMIN)) {
                        System.out.println("You don't have permission to perform this action");
                    } else {
                        tiresRepository.addTiresToTheStock(scanner);
                    }
                    break;
                case "5":
                    if (!session.getLoggedInUser().getRole().equals(UserRoles.ADMIN)) {
                        System.out.println("You don't have permission to perform this action");
                    } else {
                        System.out.print("Enter the login of the user whose role you want to change: ");
                        String login = scanner.nextLine().toLowerCase();
                        User user = userRepository.changeUserRole(login);
                        if (user != null) {
                            System.out.println("User role changed successfully.");
                        } else {
                            System.out.println("User not found.");
                        }
                    }
                    break;
                default:
                    System.out.println("Wrong choice!");
                    break;
            }
        } while (!exit);
    }

    @Override
    public void registerNewUser() {
        System.out.println("Enter a new login:");
        String login = scanner.nextLine().toLowerCase();
        System.out.println("Enter a new password:");
        String password = scanner.nextLine();
        String hashedPassword = authenticator.hashPassword(password);
        String role = UserRoles.USER;

        userRepository.addNewUserToDb(new User(login, hashedPassword, role));
    }
}
