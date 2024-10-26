package by.losik.restaurant;

public class ChiefImpl extends CookImpl implements Cook{
    public ChiefImpl(double salary, Position position, String name) {
        super(salary, position, name);
    }

    @Override
    public Order takeOrder(ListOfOrders list) {
        return (list.getListOfOrders().isEmpty()) ? null : new Executor().giveOrderToCook(this ,list.getListOfOrders().get((int) (Math.random() * list.getListOfOrders().size())),new Executor().connectToOrdersInQuery());
    }

    @Override
    public Order cookFood(ListOfOrders list) {
        return (list.getListOfOrders().isEmpty()) ? null : new Executor().deleteOrderByNameOfCookAndOrder(this, takeOrder(list), new Executor().connectToOrdersInQuery());
    }

    public Order giveOrderToACook(CookImpl cook, ListOfOrders list){
        return (list.getListOfOrders().isEmpty()) ? null : cook.cookFood(list);
    }
}
