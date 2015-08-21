package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmBlock;

public interface FilmBlockDao {
    FilmBlock save(FilmBlock filmBlock);
    void addFilm (Long idFilm);
    boolean removeNews(Film film);
    FilmBlock upDate(FilmBlock filmBlock);
    FilmBlock findByIDFilmBlock(Long idFilmBlock);
    boolean emptyTable();
    void deleteFilm(Long idFilm);
    boolean findFilm(Long id);
}
