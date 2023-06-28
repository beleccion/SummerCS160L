import java.util.List;
import java.util.ArrayList;
/**
 * This Class is a parent class for adding toppings to coffee object
 * Contains appropriate getters and setters for coffee object
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public abstract class CoffeeDecorator implements Coffee {
    private Coffee coffee;

    /**
     * Constructor takes in coffee parameter and assigns it to the class coffee object
     * @param c is the coffee object being passed in
     */
    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }

    public double getCost() { return coffee.getCost(); }

    public List<String> getIngredients() {
        return coffee.getIngredients();
    }

    public String printCoffee() {
        return coffee.printCoffee();
    }
}
