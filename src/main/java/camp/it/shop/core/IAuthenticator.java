package camp.it.shop.core;

public interface IAuthenticator {
    Session authenticate();
    String hashPassword(String password);

}
