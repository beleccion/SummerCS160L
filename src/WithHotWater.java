import java.util.ArrayList;
import java.util.List;
/**
 * This Class adds Hot Water to Coffee object
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class WithHotWater extends CoffeeDecorator {
    public WithHotWater(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost();
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.addAll(super.getIngredients());
        ingredients.add("Hot Water");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}
