import java.util.List;
/**
 * This Coffee Interface represents a generic coffee object
 *
 * Lab 6
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */

public interface Coffee {
    /**
     * getter method for the cost of a coffee
     * @return the cost of coffee
     */
    public double getCost();

    /**
     * getter method for ingredient list
     * @return a List of ingredients of the Coffee
     */
    public List<String> getIngredients();

    /**
     * print method for Coffee
     * @return a String of Coffee
     */
    public String printCoffee();
}
