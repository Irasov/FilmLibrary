package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Rating;

import java.sql.*;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.*;

public class JdbcRatingDao implements RatingDao {
    private final static String RESULT_VOTES = "votes";
    private final static String SAVE_RATING = "INSERT INTO RATING(ID,NAME, VOTES) VALUES(?,?,?)";
    private static final String FIND_BY_NAME = "SELECT * FROM RATING WHERE NAME=?";
    private static final String FIND_BY_ID = "SELECT * FROM RATING WHERE ID=?";
    private static final String DELETE_RATING = "DELETE FROM RATING WHERE ID=?";
    private static final String UPDATE_RATING = "UPDATE RATING SET VOTES = ? WHERE ID=?";;
    private static final String FIND_ID = "SELECT ID FROM RATING";


    private final Connection connection;

    public JdbcRatingDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Rating save(Rating rating) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_RATING);
            int index = 1;
            preparedStatement.setLong(index, rating.getId());
            index++;
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
    public void upDate(Long idRating, int votes) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RATING);
            int index = 1;
            preparedStatement.setInt(index, votes);
            index++;
            preparedStatement.setLong(index, idRating);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
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
    public Rating findByName(String name) {
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
    public Rating findById(Long id) {
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

    @Override
    public Long findId() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.last();
            if (!found) return null;
            return resultSet.getLong("id");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
