package camp.it.shop.gui;

import camp.it.shop.model.User;

public interface IGUI {

    String logOrReg();
    String showMenu();
    User readLoginAndPassword();

}
