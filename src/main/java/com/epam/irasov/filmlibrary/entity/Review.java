package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public class Review extends NamedEntity {
    private String text;
    private String status;
    private Rating rating;
    private LocalDate date;
    private boolean published;

    public Review() {
    }

    public Review(Long id, String name, LocalDate date, String text, String status, Rating rating, boolean published) {
        super(id, name);
        this.date = date;
        this.status = status;
        this.rating = rating;
        this.published = published;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return published == review.published && !(text != null ? !text.equals(review.text) : review.text != null) && !(status != null ? !status.equals(review.status) : review.status != null) && !(rating != null ? !rating.equals(review.rating) : review.rating != null) && !(date != null ? !date.equals(review.date) : review.date != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (published ? 1 : 0);
        return result;
    }
}
