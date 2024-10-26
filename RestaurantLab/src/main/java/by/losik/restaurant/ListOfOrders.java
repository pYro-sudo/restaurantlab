package by.losik.restaurant;

import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ListOfOrders {
    private List<Order> listOfOrders = new ArrayList<>();
    private List<Order> listOfReadyOrders = new ArrayList<>();
    public List<Order> getListOfOrders() {
        return this.listOfOrders;}
    public List<Order> getListOfReadyOrders() {
        return this.listOfReadyOrders;
    }
    public String deleteOrderByName(String name){
        return new Executor().deleteManyOrdersByNameOfGuest(name, new Executor().connectToOrder());}
    public double deleteOrderByPrice(double price){
        return new Executor().deleteManyOrdersByPrice(price, new Executor().connectToOrder());}
    public String deleteOrderByNameOfOrder(String name){
        return new Executor().deleteManyOrdersByNameOfOrder(name,new Executor().connectToOrder());}
    public FindIterable<Document> findOrderByNameOfGuest(String name){
        return new Executor().findOrderByNameOfGuest(name,new Executor().connectToOrder());}
    public FindIterable<Document> findOrderByNameOfOrder(String name){
        return new Executor().findOrderByNameOfOrder(name,new Executor().connectToOrder());}

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    public void setListOfReadyOrders(List<Order> listOfReadyOrders) {
        this.listOfReadyOrders = listOfReadyOrders;
    }
}
