package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SystemMember extends FilmMember {
    private String login;
    private String password;
    private String email;
    private List<Review> reviews;
    private List<Film> films;


    public SystemMember() {
        reviews = new ArrayList<>();
        films = new ArrayList<>();
    }

    public SystemMember(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, String login, String password, String email, Type type) {
        super(id, name, patronymic, surname, birthDate, photo,type);
        this.login = login;
        this.password = password;
        this.email = email;
        reviews = new ArrayList<>();
        films = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(o instanceof SystemMember)) return false;
        if (!super.equals(o)) return false;
        SystemMember that = (SystemMember) o;
        return !(login != null ? !login.equals(that.login) : that.login != null) && !(password != null ? !password.equals(that.password) : that.password != null) && !(email != null ? !email.equals(that.email) : that.email != null) && !(reviews != null ? !reviews.equals(that.reviews) : that.reviews != null) && !(films != null ? !films.equals(that.films) : that.films != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (reviews != null ? reviews.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }
}
