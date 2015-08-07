package com.epam.irasov.filmlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class FilmBlock extends NamedEntity {
    private List<Film> films;

    public FilmBlock() {
        this.films = new ArrayList<>();
    }

    public FilmBlock(Long id, String name) {
        super(id, name);
        this.films = new ArrayList<>();
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film){
        films.add(film);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmBlock)) return false;
        if (!super.equals(o)) return false;
        FilmBlock filmBlock = (FilmBlock) o;
        return !(films != null ? !films.equals(filmBlock.films) : filmBlock.films != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }
}
