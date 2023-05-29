package camp.it.shop.core;

import camp.it.shop.db.IUserRepository;
import camp.it.shop.gui.IGUI;
import camp.it.shop.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Authenticator implements IAuthenticator {

    @Autowired
    private IGUI gui;
    @Autowired
    private IUserRepository userRepository;

    private final String seed = "Gfdlas84Hfas*dsa&fs^gasdfg*fs8";

    @Override
    public Session authenticate() {
        int counter = 0;
        while (counter < 3) {

            User userFromGui = gui.readLoginAndPassword();
            User userFromDb = userRepository.findUserByLogin(userFromGui.getLogin());
            if (userFromDb != null &&
                    userFromDb.getPassword().equals(
                            DigestUtils.md5Hex(userFromGui.getPassword() + seed))) {
                return new Session(userFromDb);
            }

            System.out.println("Incorrect login or password !!");
            counter++;
        }

        return null;
    }

    @Override
    public String hashPassword(String password) {
        return DigestUtils.md5Hex(password + seed);
    }

}
