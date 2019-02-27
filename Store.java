/**
 * Store class
 * <p>
 * This class allows users to create a store object and has methods that allows the user to place
 * and cancel orders. It also allows users to get information about the totalRevenue and profit of the store.
 *
 * @author Anudeep Yakkala
 * @version 10/22/18
 */
public class Store {

    // You can add instance variables as needed

    private String name;

    private double totalRevenue;

    private double materialCosts;

    private DeliveryDriver[] drivers;

    private DeliveryDriver currentDriver;

    public Store(String storeName, DeliveryDriver[] drivers) {
        this(storeName, drivers.length);
        this.drivers = drivers;
    }

    public Store(String storeName, int numDrivers) {
        drivers = new DeliveryDriver[numDrivers];
        for (int i = 0; i < numDrivers; i++) {
            drivers[i] = new DeliveryDriver("Driver" + i);
        }
        name = storeName;
        totalRevenue = 0;
        materialCosts = 0;
    }

    String getStoreName() {
        return this.name;
    }

    DeliveryDriver[] getDrivers() {
        return this.drivers;
    }

    /**
     * Updates the store's financial information. This function
     * handles assigning orders to drivers if it's a delivery and
     * manages sending drivers out on delivery.
     *
     * @param item - purchased item being ordered
     */
    public void placeOrder(PurchasedItem item) {
        materialCosts += item.getMaterialCost();
        totalRevenue += item.getSalePrice();
        if (item.isDelivery()) {
            for (int i = 0; i < drivers.length; i++) {
                for (int j = 0; j < drivers[i].getMaxCapacity(); j++) {
                    if (drivers[i].getNumOrders() != drivers[i].getMaxCapacity()) {
                        drivers[i].pickupOrder(item);
                        currentDriver = drivers[i];
                        return;
                    }
                }
                drivers[i].deliverOrders();
                if (i == drivers.length - 1) {
                    i = -1;
                }
            }
        }
    }


    /**
     * Cancels an order with the store. It works under the assumption
     * that this order has already been placed. Also, this function
     * won't reduce the store's total material cost, as the item is
     * already made and wasted.
     * <p>
     * <p>
     * This method will only fail to cancel an order if the item is
     * marked for delivery but the currently selected delivery driver
     * isn't holding the item / can't remove the item (it has likely
     * already been removed).
     *
     * @param item - the order to cancel
     * @return true if the order could be canceled, false otherwise
     */
    public boolean cancelOrder(PurchasedItem item) {
        if (item.isDelivery() == false) {
            totalRevenue -= item.getSalePrice();
            return true;
        }
        for (int i = 0; i < currentDriver.getOrders().length; i++) {
            if (item.equals(currentDriver.getOrders()[i])) {
                currentDriver.removeOrder(item);
                totalRevenue -= item.getSalePrice();
                return true;
            }
        }
        return false;
    }

    /**
     * Getter method for a store's totalRevenue.
     *
     * @return gross totalRevenue
     */
    public double getGrossRevenue() {

        return totalRevenue;
    }

    /**
     * Getter method for a store's material costs.
     *
     * @return material costs
     */
    public double getMaterialCosts() {


        return materialCosts;
    }

    /**
     * Calculates the store's net profit using one of these equivalent
     * equations:
     * <p>
     *
     * <i>profit = $(totalRevenue) - $(period costs)</i>
     * <p>
     * <i>profit = $(totalRevenue) - $(material costs) - $(labor costs)</i>
     *
     * @return the net operating profit of the store at this point in
     * time
     */
    public double getNetProfit() {
        double wage = 0;
        for (int i = 0; i < drivers.length; i++) {
            wage += drivers[i].getMoneyEarned();
        }
        return totalRevenue - materialCosts - wage;

    }

    /**
     * Calculates the store's net income. The traditional formula
     * used to calculate net income is:
     * <p>
     *
     * <i>income = $(profit) - $(indirect costs)</i>
     *
     * @return net income
     */
    public double getNetIncome() {

        return getNetProfit() - 50 - getNetProfit() * 0.15;
    }


    /**
     * DO NOT EDIT THIS CODE!
     */
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append(String.format("\nStore Info\n----------\nName: \"%s\"\n", this.name));
        ret.append(String.format("Revenue: $%.2f\nCosts: $%.2f\n", this.totalRevenue, this.materialCosts));
        ret.append(String.format("Profit: $%.2f\nIncome: $%.2f\n", this.getNetProfit(), this.getNetIncome()));

        ret.append(String.format("\nDriver Info\n-----------\n"));
        int i = 1;
        for (DeliveryDriver driver : this.drivers)
            ret.append(String.format("%d.) %s\n", i++, driver.toString()));

        return ret.toString();
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    private void printStatistics(double expRevenue, double expProfit, double expIncome) {
        double revenue = this.getGrossRevenue();
        System.out.printf("Revenue: $%.2f\t\tExpected: $%.2f\t\t%% Diff: %f%%\n",
                revenue, expRevenue, percentDiff(expRevenue, revenue));

        double profit = this.getNetProfit();
        System.out.printf("Profit: $%.2f\t\tExpected: $%.2f\t\t%% Diff: %f%%\n",
                profit, expProfit, percentDiff(expProfit, profit));

        double income = this.getNetIncome();
        System.out.printf("Income: $%.2f\t\tExpected: $%.2f\t%% Diff: %f%%\n",
                income, expIncome, percentDiff(expIncome, income));
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    private static double percentDiff(double from, double to) {
        return Math.abs((to - from) / from * 100.0);
    }

}
