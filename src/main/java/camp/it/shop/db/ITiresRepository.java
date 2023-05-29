package camp.it.shop.db;

import camp.it.shop.core.Session;
import camp.it.shop.model.Tires;

import java.util.Collection;
import java.util.Scanner;

public interface ITiresRepository {
    void sellTires(Scanner scanner);
    void addTires(Tires tires);
    void addTiresToTheStock(Scanner scanner);
    void listTires(Collection<Tires> tires, Session loggedInUser);
    Collection<Tires> getTires();
    Collection<Tires> getTires(String role);
}
