import java.util.ArrayList;
import java.util.List;
/**
 * This class implements the coffee interface and represents a specific type of coffee, Espresso
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class Espresso implements Coffee {
    @Override
    public double getCost() {
        return 1.75;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Espresso");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return "An espresso";
    }
}

