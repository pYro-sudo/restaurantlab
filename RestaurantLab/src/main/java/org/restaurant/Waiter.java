package org.restaurant;

import org.json.JSONObject;

public interface Waiter {
    Order getOrder(GuestImpl guest);
    Order addOrderToList(Order order, ListOfOrders list);
}
