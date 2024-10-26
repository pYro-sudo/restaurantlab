package org.restaurant;

public class CookImpl extends Staff implements Cook {
    public CookImpl(double salary, Position position, String name) {
        super(salary, position, name);
    }

    @Override
    public Order takeOrder(ListOfOrders list) {
        Order order = new Executor().giveOrderToCook(this ,list.getListOfOrders().get((int) (Math.random() * list.getListOfOrders().size())),new Executor().connectToOrdersInQuery());
        list.getListOfOrders().add(order);
        return order;
    }

    @Override
    public Order cookFood(ListOfOrders list) {
        Order order = new Executor().deleteOrderByNameOfCookAndOrder(this, takeOrder(list), new Executor().connectToOrdersInQuery());
        list.getListOfReadyOrders().add(order);
        list.getListOfOrders().remove(order);
        return order;
    }
}
