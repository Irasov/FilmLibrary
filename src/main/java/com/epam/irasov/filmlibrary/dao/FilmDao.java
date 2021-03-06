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
    List<Long> findReviewsInFilm(Long idFilm);
    boolean findMember(Long idFilm, Long idMember);
    void removeReview(Long id);
    Film findByName(String name);
}
