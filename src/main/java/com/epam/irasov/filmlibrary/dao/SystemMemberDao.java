package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Member;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import java.time.LocalDate;
import java.util.List;

public interface SystemMemberDao {
    SystemMember findById(Long id);
    SystemMember findBySurName(String surName);
    List<SystemMember> selectSystemMembers();
    void update (SystemMember systemMember);
    SystemMember save (SystemMember systemMember);
    Member.Type saveType (SystemMember.Type type);
    int findType ();
    Member.Type insertMemberType(Member.Type type);
    boolean remove(SystemMember systemMember);
}
