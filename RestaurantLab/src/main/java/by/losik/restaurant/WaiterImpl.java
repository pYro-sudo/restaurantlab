package by.losik.restaurant;

import org.jetbrains.annotations.NotNull;

public class WaiterImpl extends Staff implements Waiter {
    public WaiterImpl(double salary, Position position, String name) {
        super(salary, position, name);
    }

    private Order order;
    @Override
    public Order getOrder(@NotNull GuestImpl guest) {
        this.order = guest.getOrder();
        new Executor().insertIntoGuests(guest, new Executor().connectToGuests());
        return guest.getOrder();
    }

    @Override
    public Order addOrderToList(Order order, ListOfOrders list) {
        list.getListOfOrders().add(order);
        this.order = order;
        return new Executor().insertOrder(order,new Executor().connectToOrder());
    }

    public Order getOrderSelf() {
        return order;
    }
}
