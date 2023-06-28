import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * This Class represents a Coffee Order
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;

    /**
     * Coffee Order Constructor
     * initializes list of Coffee objects
     * initializes LocalDateTime
     */
    public CoffeeOrder() {
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }

    /**
     * CoffeeOrder additional constructor that takes in an orderDate
     * @param orderDate
     */
    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<Coffee>();
        this.orderDate = orderDate;
    }

    /**
     * Takes in Coffee object and adds to coffees list
     * @param c Coffee object
     */
    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    public List<Coffee> getCoffees() { return coffees; }

    public LocalDateTime getOrderDate() { return orderDate; }

    /**
     * Takes cost of all coffee objects within list and totals them
     * @return the cumulative total of the coffees
     */
    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        return total;
    }

    /**
     * Creates an order receipt as a String that contains the date, and lists each coffee
     * @return a String of the newly created receipt
     */
    public String printOrder() {
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"))));
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }
        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        return order.toString();
    }
}
