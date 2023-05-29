package camp.it.shop.db;

import camp.it.shop.core.Session;
import camp.it.shop.model.Tires;
import camp.it.shop.model.UserRoles;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TiresRepository implements ITiresRepository {

    private final Map<String, Tires> tires = new HashMap<>();

    public TiresRepository() {
    }

    @Override
    public Collection<Tires> getTires() {
        return new ArrayList<>(tires.values());
    }

    @Override
    public Collection<Tires> getTires(String role) {
        List<Tires> filteredTires = new ArrayList<>();
        for (Tires tire : tires.values()) {
            if (role.equals(UserRoles.USER) && tire.getQuantity() != 0) {
                filteredTires.add(tire);
            } else if (role.equals(UserRoles.ADMIN)) {
                filteredTires.add(tire);
            }
        }
        return filteredTires;
    }

    @Override
    public void sellTires(Scanner scanner) {
        System.out.println("Enter the product code:");
        String productCode = scanner.nextLine();
        Tires selectedTires = tires.get(productCode);

        if (selectedTires != null && selectedTires.getQuantity() != 0) {
            System.out.println("Enter the quantity you want to buy:");
            int quantity = Integer.parseInt(scanner.nextLine());
            if (quantity > selectedTires.getQuantity()) {
                System.out.println("Sorry, not enough tires in the stock. You can buy maximum " + selectedTires.getQuantity() + " tires.");
            } else {
                selectedTires.setQuantity((selectedTires.getQuantity() - quantity));
                System.out.println("Great, you bought " + quantity + " tires.");
                System.out.println("The price of your purchase is: " + quantity * selectedTires.getPrice());

            }
        }
    }

    @Override
    public void addTires(Tires tires) {
        this.tires.put(tires.getProductCode(), tires);
    }

    @Override
    public void addTiresToTheStock(Scanner scanner) {
        System.out.println("Enter the product code:");
        String productCode = scanner.nextLine();
        Tires selectedTires = tires.get(productCode);
        if (selectedTires != null) {
            System.out.println("Enter the quantity you want to add:");
            String quantityStr = scanner.nextLine();
            int quantity = Integer.parseInt(quantityStr);
            selectedTires.setQuantity(selectedTires.getQuantity() + quantity);
            System.out.println("You added " + quantity + " tires. Now in the stock there is " + selectedTires.getQuantity());
        }
    }

    @Override
    public void listTires(Collection<Tires> tires, Session loggedInUser) {
        for (Tires tire : tires) {
            if (loggedInUser.getLoggedInUser().getRole().equals(UserRoles.USER) && tire.getQuantity() != 0) {
                System.out.println(tire);
            } else if (loggedInUser.getLoggedInUser().getRole().equals(UserRoles.ADMIN)) {
                System.out.println(tire);
            }
        }
    }
}








