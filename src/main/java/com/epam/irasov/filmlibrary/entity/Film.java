package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.List;

public class Film extends NamedEntity {
    private String tagLine;
    private Member director;
    private List<Member> members;
    private List<Genre> genres;
    private LocalDate premiere;
    private int ageRestriction;
    private int duration;
    private String cover;
    private String description;
    private List<Comment> comments;

    public Film() {

    }

    public Film(Long id, String name, String tagLine, Member director, List<Member> members, List<Genre> genres, LocalDate premiere, int ageRestriction, int duration, String cover, String description, List<Comment> comments) {
        super(id, name);
        this.tagLine = tagLine;
        this.director = director;
        this.members = members;
        this.genres = genres;
        this.premiere = premiere;
        this.ageRestriction = ageRestriction;
        this.duration = duration;
        this.cover = cover;
        this.description = description;
        this.comments = comments;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public Member getDirector() {
        return director;
    }

    public void setDirector(Member director) {
        this.director = director;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
