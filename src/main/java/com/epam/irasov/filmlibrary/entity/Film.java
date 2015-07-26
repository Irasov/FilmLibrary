package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Film extends NamedEntity {
    private String tagLine;
    private List<FilmMember> members;
    private List<Genre> genres;
    private LocalDate premiere;
    private int ageRestriction;
    private int duration;
    private String cover;
    private String description;
    private List<Review> reviews;
    private Rating rating;
    private List<Honor> honors;

    public Film() {
        super();
        members = new ArrayList<>();
        genres = new ArrayList<>();
        reviews = new ArrayList<>();
        honors = new ArrayList<>();
    }


    public Film(Long id, String name, String tagLine, LocalDate premiere, int ageRestriction, int duration, String cover, Rating rating, String description) {
        this();
        this.tagLine = tagLine;
        this.premiere = premiere;
        this.ageRestriction = ageRestriction;
        this.duration = duration;
        this.cover = cover;
        this.rating = rating;
        this.description = description;
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

    public List<Honor> getHonors() {
        return honors;
    }

    public void setHonors(List<Honor> honors) {
        this.honors = honors;
    }

    public void addHonors(Honor honor) {
        honors.add(honor);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenres(Genre genre) {
        genres.add(genre);
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
        if (!(o instanceof Film)) return false;
        if (!super.equals(o)) return false;
        Film film = (Film) o;
        return ageRestriction == film.ageRestriction && duration == film.duration && !(tagLine != null ? !tagLine.equals(film.tagLine) : film.tagLine != null) && !(members != null ? !members.equals(film.members) : film.members != null) && !(genres != null ? !genres.equals(film.genres) : film.genres != null) && !(premiere != null ? !premiere.equals(film.premiere) : film.premiere != null) && !(cover != null ? !cover.equals(film.cover) : film.cover != null) && !(description != null ? !description.equals(film.description) : film.description != null) && !(reviews != null ? !reviews.equals(film.reviews) : film.reviews != null) && !(rating != null ? !rating.equals(film.rating) : film.rating != null) && !(honors != null ? !honors.equals(film.honors) : film.honors != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tagLine != null ? tagLine.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (premiere != null ? premiere.hashCode() : 0);
        result = 31 * result + ageRestriction;
        result = 31 * result + duration;
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + (honors != null ? honors.hashCode() : 0);
        return result;
    }
}
