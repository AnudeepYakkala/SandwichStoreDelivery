/**
 * Delivery Driver class
 * <p>
 * This class has the fields and methods for a DeliveryDriver object
 * It allows users to ask the drivers to place orders, calculate wage, etc.
 *
 * @author Anudeep Yakkala
 * @version 10/22/18
 */
public class DeliveryDriver {

    // You can add instance variables as needed

    private String name;

    private double wage;

    private int maxCarriableItems;

    private int numDeliveries;

    private int minutesDelivering;

    private int numItems;

    private PurchasedItem[] items;

    public DeliveryDriver(String name, double wage, int maxCarriableItems) {
        this(name, wage);
        this.maxCarriableItems = maxCarriableItems;
        items = new PurchasedItem[maxCarriableItems];
    }

    public DeliveryDriver(String name, double wage) {
        this(name);
        this.wage = wage;
        this.maxCarriableItems = 5;
    }

    public DeliveryDriver(String name) {
        this.name = name;
        this.wage = 7.25;
        this.maxCarriableItems = 5;
        items = new PurchasedItem[5];
        numDeliveries = 0;
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public String getName() {
        return this.name;
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public double getWage() {
        return this.wage;
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public int getTimeSpent() {
        return this.minutesDelivering;
    }


    /**
     * DO NOT EDIT THIS CODE!
     * <p>
     * Consults the number of orders that the driver has delivered
     *
     * @return number of orders delivered
     */
    public int getNumDelivered() {
        return this.numDeliveries;
    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    public int getMaxCapacity() {
        return this.maxCarriableItems;
    }


    /**
     * Add the order to the list/array of items to deliver.
     *
     * @param item - order to add
     * @return true if the item is for delivery and if the driver can
     * hold more orders, return false otherwise
     */
    public boolean pickupOrder(PurchasedItem item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null && item.isDelivery()) {
                items[i] = item;
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the number of items in the delivery list
     *
     * @return num items
     */
    public int getNumOrders() {
        numItems = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                numItems++;
            }
        }
        return numItems;

    }


    /**
     * Return an array of items to deliver.
     * the array has to be populated within the index 0 to numItems - 1
     * and of size numItems
     *
     * @return array of type PurchasedItem
     */
    public PurchasedItem[] getOrders() {
        PurchasedItem[] deliveries = new PurchasedItem[getNumOrders()];
        int counter = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                deliveries[counter] = items[i];
                counter++;
            }
        }

        return deliveries;
    }

    /**
     * Update how long the driver has been delivering and empty the
     * list of items to deliver.
     */
    public void deliverOrders() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                minutesDelivering += items[i].getDeliveryTime();
            }
            numDeliveries++;
            items[i] = null;
        }
    }


    /**
     * Check if driver is scheduled to deliver an order and remove it
     * and update the driver's counters if the item is found.
     *
     * @param item - order to remove from deliveries
     * @return true if the driver is scheduled to deliver the item,
     * false otherwise
     */
    public boolean removeOrder(PurchasedItem item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == item) {
                items[i] = null;
                return true;
            }
        }

        return false;
    }


    /**
     * Calculates the amount of money earned by the driver
     *
     * @return amount of money earned by the driver
     */
    public double getMoneyEarned() {
        if (getTimeSpent() <= 480) {
            return getTimeSpent() * (wage / 60);
        } else {
            return (480 * (wage / 60)) + ((getTimeSpent() - 480) * (1.5 * (wage / 60)));
        }

    }

    /**
     * Compares if the input object is equal to the instance
     * Two objects are equal if they are of the same type and
     * all instance variables are equal.
     *
     * @return true if they are, false if they are not
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeliveryDriver &&
                getName().equals(((DeliveryDriver) obj).getName()) &&
                getWage() == ((DeliveryDriver) obj).getWage() &&
                getTimeSpent() == ((DeliveryDriver) obj).getTimeSpent() &&
                getNumDelivered() == ((DeliveryDriver) obj).getNumDelivered();

    }

    /**
     * DO NOT EDIT THIS CODE!
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();

        ret.append("Name: ");
        ret.append(this.name);

        ret.append(" - Wage: $");
        ret.append(String.format("%.2f", this.wage));

        ret.append(" - Can Carry: ");
        ret.append(this.maxCarriableItems);

        ret.append(" items - Num Deliveries: ");
        ret.append(this.numDeliveries);

        ret.append(" - Minutes Worked: ");
        ret.append(this.minutesDelivering);
        ret.append(" min");

        ret.append(" - Currently Carrying: ");
        ret.append(this.numItems);
        ret.append(" items");

        return ret.toString();
    }

}
