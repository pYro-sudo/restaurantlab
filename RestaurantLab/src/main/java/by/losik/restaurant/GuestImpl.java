package by.losik.restaurant;

public class GuestImpl implements Guest {
    private String name;
    private Order order;

    private String opinion;

    public GuestImpl(String name){
        this.name = name;
    }

    @Override
    public Order payForTheOrder(Order order) {
        return new Executor().deleteOneOrder(order,new Executor().connectToOrder());
    }

    @Override
    public Tip tip(double amount) {
        Tip tip = new Tip(amount,getName());
        return new Executor().insertTip(tip, new Executor().connectToTip());
    }

    @Override
    public Review writeReview() {
        return new Executor().insertReviewInfo(new Review(getOpinion(),getName(),Math.random()*10),
                new Executor().connectToReview());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(String order, double price) {
        this.order = new Order(order, getName(), price);

    }

    public String getOpinion() {
        return this.opinion;}

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
