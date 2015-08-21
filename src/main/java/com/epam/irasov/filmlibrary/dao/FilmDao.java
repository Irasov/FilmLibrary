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
    void saveFilmFilmMember(Long idFilm, Long idFilmMember);
    int findByIdFilmFilmMember(Long id);
    int findByIdFilmMemberFilm(Long id);
    List<Long> findByIdFilm(Long id);
    List<Long> findByIdFilmMember(Long id);
    void deleteFilmFilmMember(Long idFilm, Long idMember);
    void removeMemberList(Long id);
    boolean findMember(Long idFilm, Long idMember);
}
