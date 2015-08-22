package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Rating;
import com.epam.irasov.filmlibrary.entity.Review;

import java.sql.*;
import java.time.LocalDate;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.RESULT_ID;
import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.RESULT_NAME;
import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcReviewDao implements ReviewDao {
    private static final String RESULT_PUBLISHED = "published";
    private static final String RESULT_TEXT = "text";
    private static final String RESULT_STATUS = "status";
    private static final String RESULT_ID_RATING = "id_rating";
    private static final String RESULT_VOTES = "votes";
    private static final String RESULT_DATE = "date";
    private final static String FIND_RATING = "SELECT * FROM RATING WHERE ID=?";
    private static final String FIND_BY_ID = "SELECT * FROM REVIEW WHERE ID=?";
    private static final String FIND_ID = "SELECT ID FROM REVIEW";
    private static final String SAVE_REVIEW = "INSERT INTO REVIEW(ID, DATE, TEXT, STATUS, NAME, PUBLISHED, ID_RATING) VALUES(?,?,?,?,?,?,?)";
    private static final String SAVE_FILM_REVIEW = "INSERT INTO FILM_REVIEW(ID_FILM, ID_REVIEW) VALUES (?,?)";
    private static final String SAVE_MEMBER_REVIEW = "INSERT INTO SYSTEM_MEMBER_REVIEW(ID_SYSTEM_MEMBER, ID_REVIEW) VALUES (?,?)";

    private final Connection connection;

    public JdbcReviewDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Review findById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            int index = 1;
            preparedStatement.setLong(index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Review review = new Review();
            review.setId(resultSet.getLong(RESULT_ID));
            review.setDate(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
            review.setText(resultSet.getString(RESULT_TEXT));
            review.setPublished(resultSet.getBoolean(RESULT_PUBLISHED));
            review.setName(resultSet.getString(RESULT_NAME));
            review.setStatus(resultSet.getString(RESULT_STATUS));
            review.setRating(findRating(resultSet.getLong(RESULT_ID_RATING)));
            return review;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Rating findRating(Long idRating) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RATING);
            int index = 1;
            preparedStatement.setLong(index, idRating);
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

    @Override
    public void save(Review review) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_REVIEW);
            setInsertReview(preparedStatement, review.getId(), review.getDate(), review.getText(), review.getStatus(), review.getRating().getId(), review.getName(), review.getPublished());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setInsertReview(PreparedStatement preparedStatement, Long id, LocalDate date, String text, String status, Long idRating, String name, boolean published) throws SQLException {
        int index = 1;
        preparedStatement.setLong(index, id);
        index++;
        preparedStatement.setDate(index, Date.valueOf(date));
        index++;
        preparedStatement.setString(index, text);
        index++;
        preparedStatement.setString(index, status);
        index++;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setBoolean(index, published);
        index++;
        preparedStatement.setLong(index, idRating);
        preparedStatement.executeUpdate();
    }

    @Override
    public void saveFilmReview(Long idFilm, Long idReview) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FILM_REVIEW);
            int index = 1;
            preparedStatement.setLong(index, idFilm);
            index++;
            preparedStatement.setLong(index, idReview);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void saveSystemMemberReview(Long idMember, Long idReview) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_MEMBER_REVIEW);
            int index = 1;
            preparedStatement.setLong(index, idMember);
            index++;
            preparedStatement.setLong(index, idReview);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
