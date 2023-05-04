public class Item {
    private String name;
    private double originalPrice;
    private double discountAmount;
    private boolean discountIsPercentage;

    public Item(String name, String originalPrice, String discount) {
        this.name = name;
        this.originalPrice = Double.parseDouble(originalPrice);
        this.discountIsPercentage = false;
        
        if (discount.contains("%")) {
            this.discountIsPercentage = true;
            try {
                this.discountAmount = Double.parseDouble(discount.substring(0, discount.length() - 1));
                if (this.discountAmount > 100){
                    this.discountAmount = 100;
                }
                
            } catch(Exception e) {
                try {
                    this.discountAmount = Double.parseDouble(discount.substring(1, discount.length()));
                    if (this.discountAmount > 100){
                        this.discountAmount = 100;
                    }
                } catch(Exception E) {
                    System.out.println("Error: Defaulting to 0% discount");
                    this.discountAmount = 0;
                }
            }
        } else if (discount.contains("$")) {
            this.discountIsPercentage = false;
            try {
                this.discountAmount = Double.parseDouble(discount.substring(0, discount.length() - 1));
            } catch(Exception e) {
                try {
                    this.discountAmount = Double.parseDouble(discount.substring(1, discount.length()));
                } catch(Exception E) {
                    System.out.println("Error: Defaulting to 0% discount");
                    this.discountAmount = 0;
                }
            }
        } else {
            discount = "0";
        }
        
        
    }

    public String getName() {
        return this.name;
    }

    public double subtotal() {
        if(this.discountIsPercentage) {
            return this.originalPrice - (this.originalPrice * (this.discountAmount / 100));
        } else if(!(this.discountIsPercentage)){
            return this.originalPrice - this.discountAmount;
        } else {
            return -1.0;
        }
    }

    public double savings() {
        if(this.discountIsPercentage) {
            return this.originalPrice * (this.discountAmount / 100);
        } else if(!(this.discountIsPercentage)){
            return this.discountAmount;
        } else {
            return -1;
        }
    }
    
    public int itemOnSale() {
        return this.discountAmount > 0 || this.discountAmount > 0 ? 1 : 0;
    }
}