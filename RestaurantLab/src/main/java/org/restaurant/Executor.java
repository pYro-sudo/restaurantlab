package org.restaurant;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Executor {
    public MongoCollection<Document> connectToEmployees(){
        try
        {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("admin");
            return database.getCollection("employee");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally{

        }
    }

    public MongoCollection<Document> connectToReview(){
        try
        {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("admin");
            return database.getCollection("review");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public MongoCollection<Document> connectToOrder(){
        try
        {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("admin");
            return database.getCollection("Order");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public MongoCollection<Document> connectToTip(){
        try
        {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("admin");
            return database.getCollection("tip");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public MongoCollection<Document> connectToOrdersInQuery(){
        try
        {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("admin");
            return database.getCollection("ordersInQuery");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public MongoCollection<Document> connectToGuests(){
        try
        {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("admin");
            return database.getCollection("guests");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public Staff insertStaffInfo(Staff staff, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("_id", staff.getName());
            document.append("position", staff.getPosition().name());
            document.append("salary",staff.getSalary());
            documentMongoCollection.insertOne(document);
            return staff;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Staff deleteStaffInfo(Staff staff, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("_id", staff.getName());
            documentMongoCollection.deleteOne(document);
            return staff;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Staff updateStaffByNameAndPromote(String name, Position position, double salary,
                                             MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("name", name);
            documentMongoCollection.updateOne(document,new Document().append("$set",new Document().append("name",name).append("position",position.name())
                    .append("salary",salary)));
            return new Staff(salary,position,name);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Staff updateStaffSalaryByName(String name, Position position, double salary, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("name", name).append("position",position.name());
            documentMongoCollection.updateOne(document,new Document("$set",new Document().append("salary",salary)));
            return new Staff(salary,position,name);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> findStaffInfoByName(String name, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("$regex", name+"/i");
            Document document1 = new Document();
            document1.append("_id",document);
            return documentMongoCollection.find(document1);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> findStaffByPosition(Position position, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("$regex", position.name()+"/i");
            Document document1 = new Document();
            document1.append("position",document);
            return documentMongoCollection.find(document1);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> getAllReviews(MongoCollection<Document> documentMongoCollection){
        try{
            return documentMongoCollection.find();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Review insertReviewInfo(Review review, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("nameOfTheReviewer", review.getNameOfTheReviewer());
            document.append("review", review.getReview());
            document.append("rating", review.getRating());
            documentMongoCollection.insertOne(document);
            return review;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> findReviewByName(String nameOfTheReviewer, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("$regex", nameOfTheReviewer+"/i");
            Document document1 = new Document();
            document1.append("nameOfTheReviewer",document);
            return documentMongoCollection.find(document1);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> findReviewByRating(double rating, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("$gte", rating);
            Document document1 = new Document();
            document1.append("rating",document);
            return documentMongoCollection.find(document1);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> findOrderByNameOfOrder(String nameOfOrder, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("$regex", nameOfOrder+"/i");
            Document document1 = new Document();
            document1.append("nameOfOrder",document);
            return documentMongoCollection.find(document1);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> findOrderByNameOfGuest(String nameOfGuest, MongoCollection<Document> documentMongoCollection){
        try{
            Document document = new Document();
            document.append("$regex", nameOfGuest+"/i");
            Document document1 = new Document();
            document1.append("nameOfGuest",document);
            return documentMongoCollection.find(document1);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Order insertOrder(Order order, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.insertOne(new Document().append("_id",order.getNameOfOrder())
                    .append("nameOfGuest",order.getNameOfGuest()).append("price",order.getPrice()));
            return order;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Order deleteOneOrder(Order order, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.deleteOne(new Document().append("_id",order.getNameOfOrder())
                    .append("nameOfGuest",order.getNameOfGuest()).append("price",order.getPrice()));
            return order;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String deleteManyOrdersByNameOfOrder(String name, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.deleteMany(new Document().append("_id",name));
            return name;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String deleteManyOrdersByNameOfGuest(String name, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.deleteMany(new Document().append("nameOfGuest",name));
            return name;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public double deleteManyOrdersByPrice(double price, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.deleteMany(new Document().append("price",new Document("$gte",price)));
            return price;
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public Order giveOrderToCook(CookImpl cook, Order order, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.insertOne(new Document().append("nameOfOrder", order.getNameOfOrder())
                    .append("nameOfGuest",order.getNameOfGuest()).append("nameOfCook",cook.getName())
                    .append("price",order.getPrice()));
            return order;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Order deleteOrderByNameOfCookAndOrder(CookImpl cook, Order order, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.deleteOne(new Document().append("nameOfOrder",new Document("$regex",order.getNameOfOrder()+"/i"))
                    .append("nameOfCook", new Document("$regex",cook.getName()+"/i")));
            return order;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Tip insertTip(Tip tip, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.insertOne(new Document().append("amount",tip.getTip())
                    .append("nameOfGuest",tip.getNameOfGuest()));
            return tip;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> getTips(MongoCollection<Document> documentMongoCollection){
        try{
            return documentMongoCollection.find();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public FindIterable<Document> getGuests(MongoCollection<Document> documentMongoCollection){
        try{
            return documentMongoCollection.find();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public GuestImpl insertIntoGuests(GuestImpl guest, MongoCollection<Document> documentMongoCollection){
        try{
            documentMongoCollection.insertOne(new Document().append("name",guest.getName()).append("order",guest.getOrder()));
            return guest;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Order> getOrders(MongoCollection<Document> documentMongoCollection){
        try{
            List<Order> orderList = new ArrayList<>();
            FindIterable<Document> documents = documentMongoCollection.find();
            for(Document document: documents){
                orderList.add(new Order(document.getString("nameOfOrder"),document.getString("nameOfGuest"),document.getDouble("price")));
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
