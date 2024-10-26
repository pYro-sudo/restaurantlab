package org.restaurant;

public class Review {
    private String review;
    private String nameOfTheReviewer;
    private double rating;

    public Review(String review, String nameOfTheReviewer, double rating) {
        this.review = review;
        this.nameOfTheReviewer = nameOfTheReviewer;
        this.rating = rating;
        new Executor().insertReviewInfo(this,new Executor().connectToReview());
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getNameOfTheReviewer() {
        return nameOfTheReviewer;
    }

    public void setNameOfTheReviewer(String nameOfTheReviewer) {
        this.nameOfTheReviewer = nameOfTheReviewer;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
