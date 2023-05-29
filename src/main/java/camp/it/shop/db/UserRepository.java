package camp.it.shop.db;

import camp.it.shop.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserRepository implements IUserRepository {

    private Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        this.users.put(user.getLogin(), user);
    }

    @Override
    public void addNewUserToDb(User user) {
        String login = user.getLogin();
        for (User existingUser : this.users.values()) {
            if (existingUser.getLogin().equals(login)) {
                System.out.println("Given login already exists.");
                return;
            }
        }
        this.users.put(user.getLogin(), user);
        System.out.println("User added correctly");
    }

    @Override
    public User findUserByLogin(String login) {
        return this.users.get(login);
    }

    public Collection<User> getUsers() {
        return this.users.values();
    }
}


