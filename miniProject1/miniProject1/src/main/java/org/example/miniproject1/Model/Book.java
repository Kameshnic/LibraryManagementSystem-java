package org.example.miniproject1.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private int genreId;
    private int bookId;
    private String title;
    private String author;
    private int isPremium;

    public Book(String bookTitle, String domain, LocalDate publishedDate) {
        this.title=bookTitle;
        this.domain=domain;
        this.publicationDate=publishedDate;
    }

    public Book(String title, int likeCount) {
        this.title=title;
        this.likes=likeCount;
    }

    public Book(int id, String title, String author, int isPremium, String domain, String shortSummary, String longSummary, int pointsRequired, String filePath, int copies, int genreId, int likes) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.isPremium = isPremium;
        this.domain = domain;
        this.shortSummary = shortSummary;
        this.longSummary = longSummary;
        this.pointsRequired = pointsRequired;
        this.filePath = filePath;
        this.copies = copies;
        this.genreId = genreId;
        this.likes = likes;
    }
    public Book( String title, String author, int isPremium, String domain, String shortSummary, String longSummary, int pointsRequired, String filePath, int copies, int genreId, int likes) {
        this.title = title;
        this.author = author;
        this.isPremium = isPremium;
        this.domain = domain;
        this.shortSummary = shortSummary;
        this.longSummary = longSummary;
        this.pointsRequired = pointsRequired;
        this.filePath = filePath;
        this.copies = copies;
        this.genreId = genreId;
        this.likes = likes;
    }
    public Book(int id, String title, String author, int isPremium, String domain, String shortSummary, String longSummary, int pointsRequired, String filePath, int copies, int likes) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.isPremium = isPremium;
        this.domain = domain;
        this.shortSummary = shortSummary;
        this.longSummary = longSummary;
        this.pointsRequired = pointsRequired;
        this.filePath = filePath;
        this.copies = copies;
        this.likes = likes;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    private String domain;
    private String shortSummary;
    private String longSummary;
    private int likes;
    private int pointsRequired;
    private List<Review> reviews;
    private int copies;
    private LocalDate publicationDate;

    public Book(String title, String authorName, int isPremium, int genreId, String shortSummary, String longSummary, int pointsRequired, String filePath, int copies,LocalDate publicationDate) {
        this.title = title;
        this.author= authorName;
        this.isPremium = isPremium;
        this.genreId = genreId;
        this.shortSummary = shortSummary;
        this.longSummary = longSummary;
        this.pointsRequired = pointsRequired;
        this.filePath = filePath;
        this.copies = copies;
        this.publicationDate=publicationDate;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setLongSummary(String longSummary) {
        this.longSummary = longSummary;
    }

    public void setShortSummary(String shortSummary) {
        this.shortSummary = shortSummary;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book() {

    }


    public int getIsPremium() {
        return isPremium;
    }

    public void setIsPremium(int isPremium) {
        this.isPremium = isPremium;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private String filePath;
    private double points;
    private String description;



    public Book(int bookId, String title, String author, int isPremium, String domain, String shortSummary, String longSummary, int pointsRequired,String filepath,int copies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isPremium = isPremium;
        this.domain = domain;
        this.shortSummary = shortSummary;
        this.longSummary = longSummary;
        this.likes = 0;
        this.pointsRequired = pointsRequired;
        this.reviews = new ArrayList<>();
        this.filePath=filepath;
        this.copies=copies;
        this.publicationDate = publicationDate;
    }

    public Book(String bookTitle, String description, double price, String filePath) {
        this.title=bookTitle;
        this.description=description;
        this.points=price;
        this.filePath=filePath;
    }


    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int isPremium() {
        return isPremium;
    }

    public String getDomain() {
        return domain;
    }

    public String getShortSummary() {
        return shortSummary;
    }

    public String getLongSummary() {
        return longSummary;
    }

    public int getLikes() {
        return likes;
    }

    public int getPointsRequired() {
        return pointsRequired;
    }

    public void addLike() {
        this.likes++;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void setPointsRequired(int pointsRequired) {
        this.pointsRequired = pointsRequired;
    }



    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCopies() {
        return this.copies;

    }

    public void setCopies() {
    }



    public int isPublic() {
        return isPremium;
    }

    public int getId() {
        return bookId;
    }
}
