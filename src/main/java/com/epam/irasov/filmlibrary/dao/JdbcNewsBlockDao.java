package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;

import java.sql.*;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcNewsBlockDao implements NewsBlockDao {
    private final static String RESULT_ID = "id";
    private final static String RESULT_DATE = "date";
    private final static String RESULT_NAME = "name";
    private static final String INSERT_NEWS_BLOCK = "INSERT INTO INFORMATION_BLOCK (NAME) VALUES (?)";
    private static final String SELECT_NEWS_BLOCK = "SELECT NAME, DATE, TEXT, IMAGE FROM NEWS WHERE ID=ANY (SELECT ID_NEWS WHERE ID_INFORMATION_BLOCK=?)";
    private static final String RESULT_TEXT = "text";
    private static final String RESULT_IMAGE = "image";

    private final Connection connection;

    public JdbcNewsBlockDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public NewsBlock save(NewsBlock newsBlock) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS_BLOCK);
            int index = 1;
            preparedStatement.setString(index, newsBlock.getName());
            preparedStatement.executeUpdate();
            return newsBlock;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addNews(News news) {

    }

    @Override
    public boolean removeNews(News news) {
        return false;
    }

    @Override
    public boolean remove(NewsBlock newsBlock) {
        return false;
    }

    @Override
    public NewsBlock upDate(NewsBlock newsBlock) {
        return null;
    }

    @Override
    public NewsBlock selectNews(Long idNewsBlock) {
        NewsBlock newsBlock = new NewsBlock();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_BLOCK);
            int index = 1;
            preparedStatement.setLong(index, idNewsBlock);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            News news = new News();
            while (found) {
                news.setId(resultSet.getLong(RESULT_ID));
                news.setName(resultSet.getString(RESULT_NAME));
                news.setDate(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
                news.setText(resultSet.getString(RESULT_TEXT));
                news.setImage(resultSet.getString(RESULT_IMAGE));
                newsBlock.addNews(news);
                found = resultSet.next();
            }
            return newsBlock;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
