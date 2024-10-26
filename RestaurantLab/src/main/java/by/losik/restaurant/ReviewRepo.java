package by.losik.restaurant;

import com.mongodb.client.FindIterable;

import org.bson.Document;
public class ReviewRepo {
    private FindIterable<Document> reviewList;
    public FindIterable<Document> getReviewList() {
        return reviewList;
    }
    public void setReviewList(){
        this.reviewList = new Executor().getAllReviews(new Executor().connectToReview());
    }
    public FindIterable<Document> findReviewByRating(double rating){
        return new Executor().findReviewByRating(rating,new Executor().connectToReview());
    }
    public FindIterable<Document> findReviewByNameOfGuest(String name){
        return new Executor().findReviewByName(name, new Executor().connectToReview());
    }
}
