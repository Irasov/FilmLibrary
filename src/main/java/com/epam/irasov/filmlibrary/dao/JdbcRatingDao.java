package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Member;
import com.epam.irasov.filmlibrary.entity.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcRatingDao implements RatingDao {
    private final static String RESULT_ID = "id";
    private final static String RESULT_VOTES = "votes";
    private final static String RESULT_NAME = "name";
    private final static String SAVE_RATING = "INSERT INTO RATING(NAME, VOTES) VALUES(?,?)";
    private static final java.lang.String FIND_BY_NAME = "SELECT * FROM RATING WHERE NAME=?";

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
}
