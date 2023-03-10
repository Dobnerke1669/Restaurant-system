package BusinessLogicLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int orderID;
    private int clientID;
    private Date orderDate;
    private int totalPrice;

    public Order(int orderID,int clientID,Date orderDate,int totalPrice)
    {
        this.orderDate=orderDate;
        this.clientID=clientID;
        this.orderID=orderID;
        this.totalPrice=totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int hashCode()
    {
        return 1;
    }
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    /*public int getTime()
    {
        String string=orderDate.toString();
    }*/

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
