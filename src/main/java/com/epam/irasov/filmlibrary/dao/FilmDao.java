package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import java.util.List;

public interface FilmDao {
    Film findById(Long id);
    Film save(Film film);
    Long remove(Long id);
    void upDate(Film film);
    List<Film> selectFilms();
    boolean emptyTable();
}
