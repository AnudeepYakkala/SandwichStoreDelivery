/**
 * Delivery Driver interface
 * <p>
 * This interface declares the methods required for an object of type PurchasedItem
 *
 * @author Anudeep Yakkala
 * @version 10/22/18
 */
public interface PurchasedItem {
    public boolean isDelivery();

    public String getCustomerName();

    public int getDeliveryTime();

    public void setDeliveryTime(int time);

    public double getMaterialCost();

    public double getSalePrice();

}
