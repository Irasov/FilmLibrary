package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class JdbcNewsDao implements NewsDao {
    private final static String SAVE_NEWS="INSERT INTO NEWS(ID, NAME, DATE, TEXT, IMAGE) VALUES(?,?,?,?,?)";
    private final static String FIND_BY_ID_NEWS="";
    private final Connection connection;

    public JdbcNewsDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public News save(News news) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEWS);
            setInsertNews(preparedStatement,news.getId(), news.getName(), news.getDate(), news.getText(), news.getImage());
            return news;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setInsertNews(PreparedStatement preparedStatement,Long id, String name, LocalDate date, String text, String image) throws SQLException{
        int index = 1;
        preparedStatement.setLong(index,id);
        index++;
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
    public News findByIDNews(Long id) {
        return null;
    }
}
