import java.io.*;
import java.util.*;

/** The main method prompts menu of available options the program can perform for the user
 *
 * Lab 6
 *
 * CS160Lab Summer 2023
 * @author Brayden Eleccion
 * 6/27/2023
 */
public class Main {
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>();
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt";

    public static void main(String[] args) {

        System.out.println("Welcome to Java Coffee Co.!");
        inventory = readInventory(inventoryFile); // initializing inventory Map With the Inventory.txt
        try (Scanner input = new Scanner(System.in)) {
            boolean addOrder;
            boolean validInput = false;
            int userChoice = 0;
            do {
                while (!validInput){
                    try {
                        System.out.println("What would you like to do?");
                        System.out.println("\t1. Create new order");
                        System.out.println("\t2. Reload Inventory");
                        System.out.println("\t3. Update Inventory");
                        System.out.println("\t4. Update Order Log");
                        System.out.println("\t5. Restock Inventory");
                        System.out.println("\t6. Exit Application");
                        userChoice = input.nextInt();
                        switch (userChoice) {
                            //option 1 new order
                            case 1: {
                                CoffeeOrder order = buildOrder();
                                orders.add(order);
                                System.out.println(order.printOrder());
                                break;
                            }
                            //option 2 Reload Inventory
                            case 2: {
                                inventory = readInventory(inventoryFile);
                                //loop similiar to the writeinventory, but this outputs the currInventory to user
                                System.out.println("Current Inventory: ");
                                for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                                    String ingredient = entry.getKey();
                                    int quantity = entry.getValue();
                                    String line = ingredient + " = " + quantity;
                                    System.out.println(line);
                                }
                                break;
                            }
                            //option 3 Update Inventory
                            case 3: {
                                writeInventory(inventoryFile);
                                break;
                            }
                            //option 4 update OrderLog
                            case 4: {
                                writeOrderLog(logFile);
                                break;
                            }
                            //option 5 Restock Inventory with a new shipment of ingredients
                            case 5: {
                                restockInventory();
                                break;
                            }
                            //option 6 exit
                            case 6: {
                                System.out.println("Exiting Application");
                                writeInventory(inventoryFile);
                                writeOrderLog(logFile);
                                System.exit(0);
                                break;
                            }
                            //exception will be thrown if input is invalid
                            default: {
                                throw new InputMismatchException();
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! Please enter a valid input.");
                        //clear input with next Line
                        input.nextLine();
                    }
                }
                System.out.println("\nWould you like to enter another order (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addOrder = !yn.equalsIgnoreCase("N");
            } while (addOrder);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (orders.size() > 0) writeOrderLog(logFile);
    }

    /**
     * Creates a new Coffee Order
     *
     * Gives the user various prompts to create a customized coffee Order
     * @return the Coffee Order the User has created
     */
    private static CoffeeOrder buildOrder() {
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            while (addCoffee) {
                // prompt user to select base coffee type
                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                Coffee coffee;

                int option = 0;
                while (option < 1 || option > 2) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        if (option < 1 || option > 2) System.out.println("Please enter a valid option.");
                    }
                }
                input.nextLine(); // nextInt() doesn't consume newline

                if (option == 2) {
                    // Espresso is a specific case
                    //val represents the amount of item currently in inventory
                    int val = inventory.get("Espresso");
                    if (val <=0){
                        System.out.println("There is no more Espresso!");
                        continue;
                    }
                    val--;
                    inventory.put("Espresso",val);
                    coffee = new Espresso();
                } else {
                    // make BlackCoffee the default case
                    int val = inventory.get("Black Coffee");
                    if (val <=0){
                        System.out.println("There is no more Black Coffee!");
                        continue;
                    }
                    val--;
                    inventory.put("Black Coffee",val);
                    coffee = new BlackCoffee();
                }
                // prompt user for any customizations
                while (option != 0) {
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();
                    coffee = switch (option) {
                        case 1 : {
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max) {
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else {
                                    option = input.nextInt();
                                    if (option < 1 || option > max) System.out.println("Please enter a valid option.");
                                }
                            }
                            input.nextLine();
                            WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                            String s;
                            s = flavor.name() + " Syrup";
                            int val = inventory.get(s);
                            if(val > 0){
                                val--;
                                inventory.put(s, val);
                                option = 1;
                                yield new WithFlavor(coffee, flavor);
                            }
                            else {
                                System.out.println("No more " + s + "!");
                                yield coffee;
                            }
                        }
                        case 2 : {
                            int val = inventory.get("Hot Water");
                            if (val > 0){
                                val--;
                                inventory.put("Hot Water", val);
                                yield new WithHotWater(coffee);
                            }
                            else {
                                System.out.println("No more hot water!");
                                yield coffee;
                            }
                        }
                        case 3 : {
                            int val = inventory.get("Milk");
                            if (val >0){
                                val--;
                                inventory.put("Milk", val);
                                yield new WithMilk(coffee);
                            }
                            else{
                                System.out.println("No more Milk!");
                                yield coffee;
                            }
                        }
                        case 4 : {
                            int val = inventory.get("Sugar");
                            if (val > 0){
                                val--;
                                inventory.put("Sugar", val);
                                yield new WithSugar(coffee);
                            }
                            else {
                                System.out.println("No more Sugar!");
                                yield coffee;
                            }
                        }
                        case 5 : {
                            int val = inventory.get("Whipped Cream");
                            if (val > 0){
                                val--;
                                inventory.put("Whipped Cream", val);
                                yield new WithWhippedCream(coffee);
                            }
                            else {
                                System.out.println("No more Whipped Cream!");
                                yield coffee;
                            }
                        }
                        default : {
                            if (option != 0) System.out.println("Please enter valid option.");
                            yield coffee;
                        }
                    };
                }
                order.addCoffee(coffee);

                System.out.println("Would you like to order another coffee (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addCoffee = !yn.equalsIgnoreCase("N");
            }
        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }

    /**
     * reads from Inventory.txt/filePath to populate the TreeMap Inventory
     *
     * @param filePath
     * @return the updated TreeMap inventory
     */
    private static Map<String, Integer> readInventory(String filePath) {
        Map<String, Integer> inventory = new TreeMap<String, Integer>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = br.readLine()) != null){
                int equalSignIndex = line.indexOf('=');
                //subString
                //.trim() removes leading and trailing spaces around the equal sign
                String ingredient = line.substring(0, equalSignIndex).trim();
                int quantity = Integer.parseInt(line.substring(equalSignIndex + 1).trim());
                inventory.put(ingredient, quantity);
            }
        }
        catch (Exception e){
            System.out.println("Error!" + e.getMessage());
        }
        return inventory;
    }

    /**
     * uses inventory Map to write current inventory to Inventory.txt
     * @param filePath
     */
    private static void writeInventory(String filePath) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            //enhanced for loop similar to WriteOrderLog; entrySet()

            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String ingredient = entry.getKey();
                int quantity = entry.getValue();
                String line = ingredient + " = " + quantity;
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
            System.out.println("Successfully written inventory!");
        }
        catch (Exception e) {
            System.out.println("Error writing inventory: " + e.getMessage());
        }

    }

    /**
     * Models a new shipment of supplies
     * adds/restocks to existing map inventory, and calls writeInventory() to update Inventory.txt
     */
    private static void restockInventory() {
        System.out.println("New Shipment Arrived!");
        String[] ingredients = {"Black Coffee", "CARAMEL Syrup", "Espresso", "Hot Water", "MOCHA Syrup", "Milk", "Sugar", "VANILLA Syrup", "Whipped Cream"};
        int[] quantities = {15,5,15,10,5,10,10,5,10};
        for (int i = 0; i<ingredients.length;i++){
            String ingredient = ingredients[i];
            int quantity = quantities[i];
            inventory.put(ingredient, inventory.getOrDefault(ingredient,0) + quantity);
        }
        //all writeInventory to apply new inventory to Inventory.txt
        writeInventory(inventoryFile);
        //Print out Current Inventory to User
        System.out.println("Successfully Restocked!");
        System.out.println("Current Inventory: ");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            String ingredient = entry.getKey();
            int quantity = entry.getValue();
            String line = ingredient + " = " + quantity;
            System.out.println(line);
        }

    }

    /**
     * Adds the Coffee Order the user has created to OrderLog.txt
     * @param filePath
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            System.out.println("Successfully written order log!");
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    /**
     * Checks whether or not an ingredient is in stock
     * @param i - takes in an ingredient as the parameter
     * @return true if ingredient is in inventory and in stock, else false
     */
    private static boolean isInInventory(String i) {
        int quantity;
        if (inventory.containsKey(i) && ((quantity = inventory.get(i)) > 0 )){
            return true;
        }
        return false;
    }
}