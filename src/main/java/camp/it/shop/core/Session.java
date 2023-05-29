package camp.it.shop.core;

import camp.it.shop.model.User;

public class Session{

    private User loggedInUser;

    public Session(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }
}

