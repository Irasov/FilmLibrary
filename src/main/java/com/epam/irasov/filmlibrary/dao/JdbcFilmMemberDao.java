package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.FilmMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.*;

public class JdbcFilmMemberDao implements FilmMemberDao {
    private final static String SAVE_FILM_MEMBER_TYPE = "INSERT INTO FILM_MEMBER_TYPE(ID,NAME) VALUES(?,?)";
    private static final String FIND_FILM_MEMBER_TYPE = "SELECT ID FROM FILM_MEMBER_TYPE";
    private static final String SELECT_ALL_TYPES = "SELECT * FROM FILM_MEMBER_TYPE";
    private final Connection connection;

    public JdbcFilmMemberDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FilmMember.Type saveType(FilmMember.Type type) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FILM_MEMBER_TYPE);
            int index = 1;
            preparedStatement.setLong(index, type.getId());
            index++;
            preparedStatement.setString(index, type.getName());
            preparedStatement.executeUpdate();
            return type;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findType() {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FILM_MEMBER_TYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                count++;
                found = resultSet.next();
            }
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FilmMember.Type> select() {
        List<FilmMember.Type> types = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPES);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                types.add(new FilmMember.Type(resultSet.getLong(RESULT_ID), resultSet.getString(RESULT_NAME)));
                found = resultSet.next();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return types;
    }
}
