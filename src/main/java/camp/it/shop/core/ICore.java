package camp.it.shop.core;

public interface ICore {

    Session getSession();
    void start();
    void menuAfterLogIn();
    void registerNewUser();

}
