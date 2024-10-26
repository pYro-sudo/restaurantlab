package by.losik.restaurant;

public interface Guest {
    Order payForTheOrder(Order order);
    Tip tip(double amount);
    Review writeReview();
}
