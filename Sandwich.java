/**
 * Sandwich class
 * <p>
 * This class has the fields and methods for a sandwich object and implements the PurchasableItem Interface
 * It allows for a sandwich objct to be created and allows the user to get information about that sandwich object
 *
 * @author Anudeep Yakkala
 * @version 10/22/18
 */
public class Sandwich implements PurchasedItem {
    private String name;
    private double matCost;
    private double sellPrice;
    private Spicyness level;
    private int condiments;
    private int delTime;
    public static double costOfCondiment = 0.05;
    public static double pricePerCondiment = 0.75;

    public Sandwich(String name, double matCost) {
        this.name = name;
        this.matCost = matCost;
        this.condiments = 0;
        this.level = Spicyness.MILD;
        this.delTime = 0;
        this.sellPrice = 3.5 * matCost;
    }

    public Sandwich(String name, double matCost, double sellPrice) {
        this(name, matCost);
        this.sellPrice = sellPrice;
    }

    public Sandwich(String name, double matCost, double sellPrice,
                    int delTime, Spicyness level, int condiments) {
        this(name, matCost, sellPrice);
        this.delTime = delTime;
        this.level = level;
        this.condiments = condiments;
        this.matCost = matCost + condiments * costOfCondiment;
        this.sellPrice = sellPrice + condiments * pricePerCondiment;
    }

    @Override
    public boolean isDelivery() {
        return delTime > 0 && delTime <= 60;
    }

    @Override
    public String getCustomerName() {
        return name;
    }

    @Override
    public int getDeliveryTime() {
        return delTime;
    }

    @Override
    public void setDeliveryTime(int time) {
        if (time >= 0) {
            this.delTime = time;
        }
    }

    @Override
    public double getMaterialCost() {
        return matCost;
    }

    @Override
    public double getSalePrice() {
        return sellPrice;
    }

    public Spicyness getSpicyness() {
        return level;
    }

    public void setSpicyness(Spicyness spicyness) {
        this.level = spicyness;
    }

    public void addCondiments(int num) {
        condiments += num;
        this.matCost = matCost + num * costOfCondiment;
        this.sellPrice = sellPrice + num * pricePerCondiment;
    }

    public void removeCondiments(int num) {
        if (num > condiments) {
            this.matCost = matCost - condiments * costOfCondiment;
            this.sellPrice = sellPrice - condiments * pricePerCondiment;
            condiments = 0;
        } else {
            condiments = condiments - num;
            this.matCost = matCost - num * costOfCondiment;
            this.sellPrice = sellPrice - num * pricePerCondiment;
        }
    }

    public int getNumCondiments() {
        return condiments;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Sandwich &&
                getCustomerName().equals(((Sandwich) obj).getCustomerName()) &&
                getDeliveryTime() == ((Sandwich) obj).getDeliveryTime() &&
                getSpicyness() == ((Sandwich) obj).getSpicyness() &&
                getMaterialCost() == ((Sandwich) obj).getMaterialCost() &&
                getSalePrice() == ((Sandwich) obj).getSalePrice());
    }

}
