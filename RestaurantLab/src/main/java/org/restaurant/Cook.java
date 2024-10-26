package org.restaurant;

public interface Cook {
    Order takeOrder(ListOfOrders list);
    Order cookFood(ListOfOrders list);
}
