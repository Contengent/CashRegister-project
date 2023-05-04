import java.util.Scanner;

public class CashRegister {
    private static final int MAX_ITEMS = 10;
    private static final double hst = 0.13;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Item[] items = new Item[MAX_ITEMS];
        int numItems = 1;
        double subtotal = 0.0;
        double totalSavings = 0.0;
        int numDiscountedItems = 0;
        
        String stopOrContinue = "a";
        boolean errorCheck = true;
        
        String nameInput = "";
        String priceInput = "";
        String discountInput = "";
        
        System.out.println("Enter up to " + MAX_ITEMS + " items, or 'exit' to exit:");
        
        while (numItems <= MAX_ITEMS) {
            /* Continue? */
            if (numItems > 1) {
                while(true){
                    try {
                        System.out.print("Continue(y or n): ");
                        stopOrContinue = scanner.nextLine();
                        stopOrContinue.charAt(0);
                        if (stopOrContinue.charAt(0) == 'y'){
                            break;
                        } else if (stopOrContinue.charAt(0) == 'n'){
                            break;
                        } else if (stopOrContinue.toLowerCase().contains("exit")) {
                            System.out.println("Exiting...");
                            System.exit(0);
                        }
                    }
                    catch(Exception e) {
                        System.out.println("Error: Enter a valid input.");
                    }
                }
            }

            if(stopOrContinue.charAt(0) == 'n'){
                break;
            }
            
            
            /* Product name */
            while(true){
                try {
                    System.out.print("Product " + numItems + " name: ");
                    nameInput = scanner.nextLine();
                    if (nameInput.toLowerCase().contains("exit")) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    } else {
                        nameInput.charAt(0);
                        break;
                    }
                }
                
                catch(Exception e) {
                    System.out.println("Error: Enter a valid input.");
                }
            }


            /* Product price */
            while(true){
                try {
                    System.out.print("Original " + numItems + " price: $");
                    priceInput = scanner.nextLine();
                    if (priceInput.toLowerCase().contains("exit")) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    } else {
                        Double.parseDouble(priceInput);
                        break;
                    }
                }
                catch(Exception e) {
                    System.out.println("Error: Enter a valid input.");
                }
            }
            
            
            /* Product discount */
            while(true){
                try {
                    System.out.print("Discount " + numItems + " discount (%, or $: ");
                    discountInput = scanner.nextLine();
                    if (discountInput.toLowerCase().contains("exit")) {
                        System.out.println("Exiting...");
                        System.exit(0);
                    } else if (discountInput.contains("%")){
                        if (discountInput.charAt(0) == '%'){
                            Double.parseDouble(discountInput.substring(1, discountInput.length()));
                            break;
                        } else if (discountInput.charAt(discountInput.length() - 1) == '%' ){
                            Double.parseDouble(discountInput.substring(0, discountInput.length() - 1));
                            break;
                        } else if (discountInput.toLowerCase() == "exit") {
                            System.exit(0);
                        } else {
                            Double.parseDouble(discountInput);
                            break;
                        } 
                    } else if (discountInput.contains("$")){
                        if (discountInput.charAt(0) == '$'){
                            Double.parseDouble(discountInput.substring(1, discountInput.length()));
                            break;
                        } else if (discountInput.charAt(discountInput.length() - 1) == '$' ){
                            Double.parseDouble(discountInput.substring(0, discountInput.length() - 1));
                            break;
                        } else if (discountInput.toLowerCase() == "exit") {
                            System.exit(0);
                        } else {
                            Double.parseDouble(discountInput);
                            break;
                        }
                    } else {
                        discountInput = "0";
                        System.out.println("Error: Enter a valid input.");
                    }
                }
                catch(Exception e) {
                    System.out.println("Error: Enter a valid input.");
                }
            }
            
            /* Product 'item' creation */
            Item item = new Item(nameInput, priceInput, discountInput);
            items[numItems] = item;
            numItems++;
            
            /* idk what to call this lol */
            subtotal += item.subtotal();
            totalSavings += item.savings();
            numDiscountedItems += item.itemOnSale();
        }
        
        double taxAmount = subtotal*hst;
        double total = subtotal + taxAmount;
        
        System.out.println("\nReceipt:");
        for (int i = 1; i < numItems; i++) {
            System.out.println(items[i].toString());
        }
        System.out.println("\nSubtotal: $" + String.format("%.2f", subtotal));
        System.out.println("HST: $" + String.format("%.2f", taxAmount));
        System.out.println("Total: $" + String.format("%.2f", total));
        System.out.println("\nYou saved $" + String.format("%.2f", totalSavings) + " on " + numDiscountedItems + " item(s) today.");
    }
}