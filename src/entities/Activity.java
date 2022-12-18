package entities;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private String type;
    private String description;
    private List<Review> reviewList;

    // Costruttore
    public Activity(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    // Aggiunge una recensione all'attività
    public void addReview(Review review) {
        if(reviewList == null) reviewList = new ArrayList<Review>();
        reviewList.add(review);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
