import java.util.ArrayList;
import java.util.List;
/**
 * This Class adds Sugar to Coffee object
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class WithSugar extends CoffeeDecorator {
    public WithSugar(Coffee c) {
        super(c);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.15;
    }

    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.addAll(super.getIngredients());
        ingredients.add("Sugar");
        return ingredients;
    }

    @Override
    public String printCoffee() {
        return super.printCoffee() + " with sugar";
    }
}
