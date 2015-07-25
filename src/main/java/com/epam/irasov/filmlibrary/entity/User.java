package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends SystemUser {
    List<Review> reviews;
    List<Rating> ratings;

    public User() {
        super();
        ratings = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public User(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, Type type, String login, String password, String email) {
        super(id, name, patronymic, surname, birthDate, photo, type, login, password, email);
        ratings = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReviews(Review review){
        reviews.add(review);
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        ratings = ratings;
    }

    public void addRatings(Rating rating){
        ratings.add(rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return !(reviews != null ? !reviews.equals(user.reviews) : user.reviews != null) && !(ratings != null ? !ratings.equals(user.ratings) : user.ratings != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (ratings != null ? ratings.hashCode() : 0);
        return result;
    }
}
