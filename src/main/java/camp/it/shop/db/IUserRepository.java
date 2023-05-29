package camp.it.shop.db;

import camp.it.shop.model.User;

import java.util.Collection;

public interface IUserRepository {

    void addUser(User user);
    void addNewUserToDb(User user);
    User findUserByLogin(String login);
    Collection<User> getUsers();

}
