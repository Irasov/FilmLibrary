package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.*;
import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcNewsDao implements NewsDao {
    private static final String RESULT_TEXT = "text";
    private static final String RESULT_DATE = "date";
    private final static String SAVE_NEWS="INSERT INTO NEWS(NAME, DATE, TEXT, IMAGE) VALUES(?,?,?,?)";
    private static final String EMPTY_TABLE = "SELECT ID FROM NEWS";
    private static final String FIND_ALL_NEWS = "SELECT * FROM NEWS";
    private final static String FIND_BY_ID = "SELECT* FROM NEWS WHERE ID = ?";
    private static final String UPDATE_NEWS = "UPDATE NEWS SET NAME = ?, TEXT = ?, DATE = ?,IMAGE=? WHERE ID=?";
    private static final String DELETE_NEWS = "DELETE FROM NEWS WHERE id=?";
    private final Connection connection;

    public JdbcNewsDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public News save(News news) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEWS);
            setInsertNews(preparedStatement,news.getName(), news.getDate(), news.getText(), news.getImage());
            return news;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setInsertNews(PreparedStatement preparedStatement,String name, LocalDate date, String text, String image) throws SQLException{
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setDate(index, Date.valueOf(date));
        index++;
        preparedStatement.setString(index, text);
        index++;
        preparedStatement.setString(index, image);
        preparedStatement.executeUpdate();
    }

    @Override
    public News findByIdNews(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            int index = 1;
            preparedStatement.setLong(index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            News item = new News();
            item.setId(resultSet.getLong(RESULT_ID));
            item.setName(resultSet.getString(RESULT_NAME));
            item.setImage(resultSet.getString(RESULT_IMAGE));
            item.setText(resultSet.getString(RESULT_TEXT));
            item.setDate(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
            return item;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean emptyTable() {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EMPTY_TABLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                count++;
                found = resultSet.next();
            }
            return count == 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<News> selectNews() {
        List<News> news = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_NEWS);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            News item;
            while (found) {
                item = new News();
                item.setId(resultSet.getLong(RESULT_ID));
                item.setName(resultSet.getString(RESULT_NAME));
                item.setImage(resultSet.getString(RESULT_IMAGE));
                item.setText(resultSet.getString(RESULT_TEXT));
                item.setDate(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
                news.add(item);
                found = resultSet.next();
            }
            return news;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void upDate(News item) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEWS);
            setUpdateNews(preparedStatement, item.getName(), item.getText(), item.getDate(), item.getImage(), item.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setUpdateNews(PreparedStatement preparedStatement, String title, String text, LocalDate date, String image, Long id) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, title);
        index++;
        preparedStatement.setString(index, text);
        index++;
        preparedStatement.setDate(index, Date.valueOf(date));
        index++;
        preparedStatement.setString(index, image);
        index++;
        preparedStatement.setLong(index, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void remove(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NEWS);
            int index = 1;
            preparedStatement.setLong(index, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
