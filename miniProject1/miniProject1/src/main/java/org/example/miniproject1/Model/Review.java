package org.example.miniproject1.Model;

public class Review {
    private int review_id;
    private int bookId;
    private String reviewerName;
    private String reviewText;
    private int rating;
    private String bookTitle;

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Review(int bookId, String reviewerName, String reviewText, int rating, String bookTitle) {
        this.bookId = bookId;
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.bookTitle=bookTitle;
    }
    public Review (String reviewerName, String reviewText, int rating, String bookTitle) {
        this.reviewerName = reviewerName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.bookTitle=bookTitle;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
