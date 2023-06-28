import java.util.List;
import java.util.ArrayList;
/**
 * This class implements the coffee interface and represents a specific type of coffee, Black Coffee
 * Lab 6
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class BlackCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Black Coffee");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}
