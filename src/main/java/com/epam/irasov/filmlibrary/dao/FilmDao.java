package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.Rating;

import java.time.LocalDate;
import java.util.List;

public interface FilmDao {
    Film findById(Long id);
    Film save(Film film);
    Long remove(Long id);
    void upDate(Film film);
    List<Film> selectFilms();
    boolean emptyTable();
    /*Movie findById(Long id);
    void update(Movie movie);
    Movie save(Movie movie);
    Movie merge(Movie movie);
    List<Movie.Member> selectMember(int countMovie);
    Movie.Member insertMember(Movie.Member member);
    boolean remove(Movie movie);
    void removeByDate(LocalDate date);*/
}
