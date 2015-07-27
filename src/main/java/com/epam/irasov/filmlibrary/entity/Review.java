package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public class Review extends NamedEntity {
    public enum Status {POSITIVE, NEGATIVE, NEUTRAL}

    private LocalDate date;
    private String text;
    private Status status;

    public Review() {
    }

    public Review(Long id, String name, LocalDate date, String text, Status status) {
        super(id, name);
        this.date = date;
        this.text = text;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return !(date != null ? !date.equals(review.date) : review.date != null) && !(text != null ? !text.equals(review.text) : review.text != null) && status == review.status;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
