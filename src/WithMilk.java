import java.util.ArrayList;
import java.util.List;
/**
 * This Class adds Milk to Coffee object
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.55;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.addAll(super.getIngredients());
        ingredients.add("Milk");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with milk";
    }
}
