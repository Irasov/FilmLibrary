package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Member;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class JdbcSystemMemberDao implements SystemMemberDao {
    private final static String SAVE_SYSTEM_MEMBER = "INSERT INTO SYSTEM_MEMBER(NAME, PATRONYMIC, SURNAME, BIRTH_DATE, ID_TYPE, LOGIN, PASSWORD, EMAIL) VALUES(?,?,?,?,?,?,?,?)";
    private final static String SAVE_SYSTEM_MEMBER_TYPE = "INSERT INTO SYSTEM_MEMBER_TYPE(NAME) VALUES(?)";
    private final Connection connection;

    public JdbcSystemMemberDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SystemMember findById(Long id) {
        return null;
    }

    @Override
    public SystemMember findBySurName(String surName) {
        return null;
    }

    @Override
    public void update(SystemMember systemMember) {

    }

    @Override
    public SystemMember save(SystemMember systemMember) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SYSTEM_MEMBER);
            setInsertSystemMember(preparedStatement, systemMember.getName(), systemMember.getPatronymic(), systemMember.getSurname(), systemMember.getBirthDate(), systemMember.getType().getId(), systemMember.getLogin(), systemMember.getPassword(), systemMember.getEmail());
            return systemMember;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Member.Type saveType(SystemMember.Type type) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SYSTEM_MEMBER_TYPE);
            int index = 1;
            preparedStatement.setString(index, type.getName());
            preparedStatement.executeUpdate();
            return type;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Member.Type insertMemberType(Member.Type type) {
        return null;
    }

    @Override
    public boolean remove(SystemMember systemMember) {
        return false;
    }

    static void setInsertSystemMember(PreparedStatement preparedStatement, String name, String patronymic, String surname, LocalDate birthDate, Long idType, String login, String password, String email) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, patronymic);
        index++;
        preparedStatement.setString(index, surname);
        index++;
        preparedStatement.setDate(index, Date.valueOf(birthDate));
        index++;
        preparedStatement.setLong(index, idType);
        index++;
        preparedStatement.setString(index, login);
        index++;
        preparedStatement.setString(index, password);
        index++;
        preparedStatement.setString(index, email);
        preparedStatement.executeUpdate();
    }
}
