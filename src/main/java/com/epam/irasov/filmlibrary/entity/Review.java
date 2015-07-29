package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public class Review extends NamedEntity {
    public enum Status {POSITIVE, NEGATIVE, NEUTRAL}

    private LocalDate date;
    private String text;
    private User author;
    private Status status;
    private Rating rating;
    private boolean published;

    public Review() {
    }

    public Review(Long id, String name, LocalDate date, String text, Status status, Rating rating, boolean published, User author) {
        super(id, name);
        this.date = date;
        this.text = text;
        this.author = author;
        this.status = status;
        this.rating = rating;
        this.published = published;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public boolean getPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return published == review.published && !(date != null ? !date.equals(review.date) : review.date != null) && !(text != null ? !text.equals(review.text) : review.text != null) && !(author != null ? !author.equals(review.author) : review.author != null) && status == review.status && !(rating != null ? !rating.equals(review.rating) : review.rating != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (published ? 1 : 0);
        return result;
    }
}
