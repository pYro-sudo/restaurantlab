package by.losik.restaurant;

public interface Waiter {
    Order getOrder(GuestImpl guest);
    Order addOrderToList(Order order, ListOfOrders list);
}
