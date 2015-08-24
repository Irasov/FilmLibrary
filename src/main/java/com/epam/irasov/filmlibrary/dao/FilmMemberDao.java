package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.FilmMember;

import java.util.List;

public interface FilmMemberDao {
    FilmMember.Type saveType(FilmMember.Type type);
    int findType();
    List<FilmMember.Type> select();
    FilmMember saveFilmMember(FilmMember member);
    boolean emptyTable();
    List<FilmMember> selectFilmMember();
    FilmMember findById(Long id);
    void upDate(FilmMember filmMember);
    Long remove(Long id);
    void removeFromFilms(Long id);
    FilmMember findBySurname(String surname);
}
