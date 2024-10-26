package org.restaurant;

public class Order {
    private String nameOfOrder;
    private String nameOfGuest;
    private double price;

    public Order(String nameOfOrder, String nameOfGuest, double price) {
        this.nameOfOrder = nameOfOrder;
        this.nameOfGuest = nameOfGuest;
        this.price = price;
    }

    public String getNameOfOrder() {
        return nameOfOrder;
    }

    public void setNameOfOrder(String nameOfOrder) {
        this.nameOfOrder = nameOfOrder;
    }

    public String getNameOfGuest() {
        return nameOfGuest;
    }

    public void setNameOfGuest(String nameOfGuest) {
        this.nameOfGuest = nameOfGuest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
