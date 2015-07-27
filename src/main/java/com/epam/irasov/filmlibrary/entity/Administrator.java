package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrator extends SystemUser {
    private List<Film> films;
    private List<Member> members;
    private List<Genre> genres;
    private List<Honor> honors;

    public Administrator() {
        films = new ArrayList<>();
        members = new ArrayList<>();
        genres = new ArrayList<>();
        honors = new ArrayList<>();
    }

    public Administrator(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, Type type, String login, String password, String email) {
        super(id, name, patronymic, surname, birthDate, photo, type, login, password, email);
        films = new ArrayList<>();
        members = new ArrayList<>();
        genres = new ArrayList<>();
        honors = new ArrayList<>();
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public List<Honor> getHonors() {
        return honors;
    }

    public void setHonors(List<Honor> honors) {
        this.honors = honors;
    }

    public void Honor(Honor honor) {
        honors.add(honor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        if (!super.equals(o)) return false;
        Administrator that = (Administrator) o;
        return !(films != null ? !films.equals(that.films) : that.films != null) && !(members != null ? !members.equals(that.members) : that.members != null) && !(genres != null ? !genres.equals(that.genres) : that.genres != null) && !(honors != null ? !honors.equals(that.honors) : that.honors != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (films != null ? films.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (honors != null ? honors.hashCode() : 0);
        return result;
    }
}
