package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.*;

public class JdbcRatingDao implements RatingDao {
    private final static String RESULT_VOTES = "votes";
    private final static String SAVE_RATING = "INSERT INTO RATING(NAME, VOTES) VALUES(?,?)";
    private static final java.lang.String FIND_BY_NAME = "SELECT * FROM RATING WHERE NAME=?";
    private static final java.lang.String FIND_BY_ID = "SELECT * FROM RATING WHERE ID=?";
    private static final String DELETE_RATING = "DELETE FROM RATING WHERE ID=?";


    private final Connection connection;

    public JdbcRatingDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Rating save(Rating rating) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RATING);
            int index = 1;
            preparedStatement.setString(index, rating.getName());
            index++;
            preparedStatement.setInt(index, rating.getVotes());
            preparedStatement.executeUpdate();
            return rating;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void upDate() {

    }

    @Override
    public void remove(Long id) {
        try {
        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RATING);
        preparedStatement.setLong(index, id);
        preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Rating findbyName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            int index = 1;
            preparedStatement.setString(index, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Rating rating = new Rating();
            rating.setId(resultSet.getLong(RESULT_ID));
            rating.setName(resultSet.getString(RESULT_NAME));
            rating.setVotes(resultSet.getInt(RESULT_VOTES));
            return rating;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Rating findbyId(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            int index = 1;
            preparedStatement.setLong(index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Rating rating = new Rating();
            rating.setId(resultSet.getLong(RESULT_ID));
            rating.setName(resultSet.getString(RESULT_NAME));
            rating.setVotes(resultSet.getInt(RESULT_VOTES));
            return rating;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
