package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import java.util.List;

public interface SystemMemberDao {
    SystemMember findById(Long id);
    SystemMember findBySurName(String surName);
    SystemMember save (SystemMember systemMember);
    SystemMember findByCredentials(String login,String password);
    FilmMember.Type saveType (SystemMember.Type type);
    int findType ();
    boolean checkForUniqueness(String login);
    boolean remove(SystemMember systemMember);
    SystemMember upDate (SystemMember systemMember);
    boolean FindPassword(Long id,String password);
    void UpDatePassword(Long id, String password);
    void removeReview(Long id);
}
