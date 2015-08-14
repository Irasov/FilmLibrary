package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import java.util.List;

public interface SystemMemberDao {
    SystemMember findById(Long id);
    SystemMember findBySurName(String surName);
    SystemMember save (SystemMember systemMember);
    SystemMember findByCredentials(String login,String password);
    List<SystemMember> selectSystemMembers();
    FilmMember.Type saveType (SystemMember.Type type);
    FilmMember.Type insertMemberType(FilmMember.Type type);
    int findType ();
    boolean checkForUniqueness(String login);
    boolean remove(SystemMember systemMember);
    void update (SystemMember systemMember);

}
