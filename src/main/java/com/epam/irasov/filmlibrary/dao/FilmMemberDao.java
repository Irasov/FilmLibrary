package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.FilmMember;

import java.util.List;

public interface FilmMemberDao {
    FilmMember.Type saveType (FilmMember.Type type);
    int findType();
    List<FilmMember.Type> select();
}
