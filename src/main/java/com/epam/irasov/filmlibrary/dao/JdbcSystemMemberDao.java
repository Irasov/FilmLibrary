package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Member;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import java.sql.*;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcSystemMemberDao implements SystemMemberDao {
    private final static String RESULT_ID = "id";
    private final static String RESULT_DATE = "date";
    private final static String RESULT_NAME = "name";
    private final static String RESULT_SURNAME = "surname";
    private final static String RESULT_PATRONYMIC = "patronymic";
    private final static String RESULT_LOGIN = "login";
    private final static String RESULT_PASSWORD = "password";
    private final static String RESULT_EMAIL = "email";
    private final static String SAVE_SYSTEM_MEMBER = "INSERT INTO SYSTEM_MEMBER(NAME, PATRONYMIC, SURNAME, BIRTH_DATE, ID_TYPE, LOGIN, PASSWORD, EMAIL) VALUES(?,?,?,?,?,?,?,?)";
    private final static String SAVE_SYSTEM_MEMBER_TYPE = "INSERT INTO SYSTEM_MEMBER_TYPE(NAME) VALUES(?)";
    private final static String FIND_BY_ID = "SELECT  ID, NAME, PATRONYMIC, SURNAME, BIRTH_DATE, ID_TYPE, LOGIN, PASSWORD, EMAIL FROM SYSTEM_MEMBER WHERE ID = ?";
    private final static String FIND_BY_ID_TYPE = "SELECT ID, NAME FROM SYSTEM_MEMBER_TYPE WHERE ID=ANY(SELECT ID_TYPE FROM SYSTEM_MEMBER WHERE ID=?)";
    private final static String UPDATE_SYSTEM_MEMBER = "UPDATE SYSTEM_MEMBER SET NAME = ?, SURNAME = ?, PATRONYMIC=?, BIRTH_DATE =  ?  WHERE ID=?";
    private final Connection connection;

    public JdbcSystemMemberDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public SystemMember findById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_TYPE);
            int index = 1;
            preparedStatement.setLong(index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Member.Type type = new Member.Type();
            type.setId(resultSet.getLong(RESULT_ID));
            type.setName(resultSet.getString(RESULT_NAME));
            preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setLong(index, id);
            resultSet = preparedStatement.executeQuery();
            found = resultSet.next();
            if (!found) return null;
            SystemMember systemMember;
            while (found) {
                systemMember = new SystemMember();
                systemMember.setId(resultSet.getLong(RESULT_ID));
                systemMember.setName(resultSet.getString(RESULT_NAME));
                systemMember.setPatronymic(resultSet.getString(RESULT_PATRONYMIC));
                systemMember.setSurname(resultSet.getString(RESULT_SURNAME));
                systemMember.setBirthDate(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
                systemMember.setType(type);
                systemMember.setLogin(resultSet.getString(RESULT_LOGIN));
                systemMember.setPassword(resultSet.getString(RESULT_PASSWORD));
                systemMember.setEmail(resultSet.getString(RESULT_EMAIL));
                found = resultSet.next();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public SystemMember findBySurName(String surName) {
        return null;
    }

    @Override
    public void update(SystemMember systemMember) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SYSTEM_MEMBER);
            setUpdateSystemMember(preparedStatement, systemMember.getName(), systemMember.getPatronymic(), systemMember.getSurname(), systemMember.getBirthDate(), systemMember.getEmail(), systemMember.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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

    private void setInsertSystemMember(PreparedStatement preparedStatement, String name, String patronymic, String surname, LocalDate birthDate, Long idType, String login, String password, String email) throws SQLException {
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

    private void setUpdateSystemMember(PreparedStatement preparedStatement, String name, String patronymic, String surname, LocalDate birthDate, String email, Long id) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, patronymic);
        index++;
        preparedStatement.setString(index, surname);
        index++;
        preparedStatement.setDate(index, Date.valueOf(birthDate));
        index++;
        preparedStatement.setString(index, email);
        index++;
        preparedStatement.setLong(index, id);
        preparedStatement.executeUpdate();
    }
}
