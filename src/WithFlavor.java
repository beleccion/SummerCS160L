import java.util.ArrayList;
import java.util.List;
/**
 * This Class extends CoffeeDecorator and contains Syrup Flavors the User can add to their coffee
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class WithFlavor extends CoffeeDecorator {
    /**
     * the Syrup enum contains the flavors of syrup that can be added to a coffee
     */
    enum Syrup {
        CARAMEL,
        MOCHA,
        VANILLA
    }

    private Syrup flavor;

    /**
     * WithFlavor is the constructor that takes in a coffee object and a syrup flavor
     *
     * @param c - coffee
     * @param s - flavor
     */
    public WithFlavor(Coffee c, Syrup s) {
        super(c);
        flavor = s;
    }

    /**
     * Get cost method add 0.35 to the existing cost
     * @return cost of coffee after adding syrup
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }

    /**
     * calls superclass and adds list of ingredients from coffee passed into withFlavor, and creates a new list with the syrup inlcuded
     * @return New list of ingredients
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.addAll(super.getIngredients()); //Adds all ingredients from super class list
        ingredients.add(flavor.name() + " Syrup");
        return ingredients;
    }

    /**
     * Creates a string by calling superclass printCoffee method and adds the syrup flavor
     * @return newly created string with new flavor
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with " + flavor;
    }
}
