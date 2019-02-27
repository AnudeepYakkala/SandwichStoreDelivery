/**
 * SideOrder class
 * <p>
 * This class has the fields and methods for a sideOrder object and implements the PurchasableItem Interface
 * It allows for a sideOrder objct to be created and allows the user to get information about that sandwich object
 *
 * @author Anudeep Yakkala
 * @version 10/22/18
 */
public class SideOrder implements PurchasedItem {

    private String name;
    private double matCost;
    private double sellPrice;
    private int delTime;
    private OrderSize size;

    public SideOrder(String name, double matCost, double sellPrice) {
        this.name = name;
        this.size = OrderSize.SMALL;
        this.matCost = matCost;
        this.sellPrice = sellPrice;
        this.delTime = 0;
    }

    public SideOrder(String name, double matCost, double sellPrice, int delTime) {
        this(name, matCost, sellPrice);
        this.delTime = delTime;
    }

    public SideOrder(String name, double matCost, double sellPrice, int delTime, OrderSize size) {
        this(name, matCost, sellPrice, delTime);
        this.size = size;
        if (size == OrderSize.SMALL) {
        } else if (size == OrderSize.MEDIUM) {
            this.matCost = matCost + 0.4;
            this.sellPrice = sellPrice + 2;
        } else if (size == OrderSize.LARGE) {
            this.matCost = matCost + 0.8;
            this.sellPrice = sellPrice + 3;
        } else if (size == OrderSize.ABSURD) {
            this.matCost = matCost + 1.5;
            this.sellPrice = sellPrice + 4.5;
        }
    }

    @Override
    public boolean isDelivery() {
        return delTime > 0 && delTime <= 30;
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
            delTime = time;
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

    public OrderSize getOrderSize() {
        return size;
    }

    public void setOrderSize(OrderSize newSize) {
        if (newSize == OrderSize.SMALL) {
        } else if (newSize == OrderSize.MEDIUM) {
            this.matCost = matCost + 0.4;
            this.sellPrice = sellPrice + 2;
        } else if (newSize == OrderSize.LARGE) {
            this.matCost = matCost + 0.8;
            this.sellPrice = sellPrice + 3;
        } else if (newSize == OrderSize.ABSURD) {
            this.matCost = matCost + 1.5;
            this.sellPrice = sellPrice + 4.5;
        }
        if (newSize == OrderSize.SMALL) {
        } else if (this.size == OrderSize.MEDIUM) {
            this.matCost = matCost - 0.4;
            this.sellPrice = sellPrice - 2;
        } else if (this.size == OrderSize.LARGE) {
            this.matCost = matCost - 0.8;
            this.sellPrice = sellPrice - 3;
        } else if (this.size == OrderSize.ABSURD) {
            this.matCost = matCost - 1.5;
            this.sellPrice = sellPrice - 4.5;
        }
        this.size = newSize;
    }

    public boolean equals(Object obj) {
        return obj instanceof SideOrder &&
                getCustomerName().equals(((SideOrder) obj).getCustomerName()) &&
                getDeliveryTime() == ((SideOrder) obj).getDeliveryTime() &&
                getMaterialCost() == ((SideOrder) obj).getMaterialCost() &&
                getSalePrice() == ((SideOrder) obj).getSalePrice() &&
                getOrderSize().equals(((SideOrder) obj).getOrderSize());
    }
}
