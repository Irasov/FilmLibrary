package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Film extends NamedEntity {
    private String tagLine;
    private List<FilmMember> members;
    private String genre;
    private LocalDate premiere;
    private int ageRestriction;
    private int duration;
    private String cover;
    private String description;
    private List<Review> reviews;
    private Rating rating;

    public Film() {
        super();
        members = new ArrayList<>();
        reviews = new ArrayList<>();
    }


    public Film(Long id, String name, String tagLine, String genre, LocalDate premiere, int ageRestriction, int duration, String cover, Rating rating, String description) {
        super(id, name);
        this.tagLine = tagLine;
        this.premiere = premiere;
        this.ageRestriction = ageRestriction;
        this.duration = duration;
        this.cover = cover;
        this.rating = rating;
        this.description = description;
        this.genre = genre;
        members = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public List<FilmMember> getMembers() {
        return members;
    }

    public void setMembers(List<FilmMember> members) {
        this.members = members;
    }

    public void addMember(FilmMember member) {
        members.add(member);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getPremiere() {
        return premiere;
    }

    public void setPremiere(LocalDate premiere) {
        this.premiere = premiere;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReviews(Review review) {
        reviews.add(review);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Film film = (Film) o;
        return ageRestriction == film.ageRestriction && duration == film.duration && !(tagLine != null ? !tagLine.equals(film.tagLine) : film.tagLine != null) && !(members != null ? !members.equals(film.members) : film.members != null) && !(genre != null ? !genre.equals(film.genre) : film.genre != null) && !(premiere != null ? !premiere.equals(film.premiere) : film.premiere != null) && !(cover != null ? !cover.equals(film.cover) : film.cover != null) && !(description != null ? !description.equals(film.description) : film.description != null) && !(reviews != null ? !reviews.equals(film.reviews) : film.reviews != null) && !(rating != null ? !rating.equals(film.rating) : film.rating != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tagLine != null ? tagLine.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (premiere != null ? premiere.hashCode() : 0);
        result = 31 * result + ageRestriction;
        result = 31 * result + duration;
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }
}
