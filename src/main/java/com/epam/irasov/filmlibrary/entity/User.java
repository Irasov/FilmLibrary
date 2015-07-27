package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User extends SystemUser {
    List<Review> reviews;
    List<Film> films;

    public User() {
        super();
        films = new ArrayList<>();
        reviews = new ArrayList<>();
    }

    public User(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, Type type, String login, String password, String email) {
        super(id, name, patronymic, surname, birthDate, photo, type, login, password, email);
        films = new ArrayList<>();
        reviews = new ArrayList<>();
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

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return !(reviews != null ? !reviews.equals(user.reviews) : user.reviews != null) && !(films != null ? !films.equals(user.films) : user.films != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }
}
