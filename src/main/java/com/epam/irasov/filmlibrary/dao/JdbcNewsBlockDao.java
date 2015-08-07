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
    private static final String INSERT_NEWS_BLOCK = "INSERT INTO INFORMATION_BLOCK (ID,NAME) VALUES (?,?)";
    private static final String SELECT_NEWS_BLOCk = "SELECT NAME FROM INFORMATION_BLOCK WHERE ID=?";
    private static final String SELECT_NEWS = "SELECT ID, NAME, DATE, TEXT, IMAGE FROM NEWS WHERE iD=ANY(SELECT ID_NEWS FROM INFORMATION_BLOCK_NEWS WHERE ID_INFORMATION_BLOCK=?)";
    private static final String ADD_NEWS="INSERT INTO INFORMATION_BLOCK_NEWS(ID_INFORMATION_BLOCK, ID_NEWS) VALUES (?,?)";
    private static final String RESULT_TEXT = "text";
    private static final String RESULT_IMAGE = "image";
    private static final String EMPTY_TABLE = "SELECT ID FROM INFORMATION_BLOCK_NEWS";

    private final Connection connection;

    public JdbcNewsBlockDao(Connection connection) {
        this.connection = connection;
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
    public NewsBlock save(NewsBlock newsBlock) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEWS_BLOCK);
            int index = 1;
            preparedStatement.setLong(index, newsBlock.getId());
            index++;
            preparedStatement.setString(index, newsBlock.getName());
            preparedStatement.executeUpdate();
            return newsBlock;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addNews(NewsBlock newsBlock, News news) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEWS);
            int index = 1;
            preparedStatement.setLong(index, newsBlock.getId());
            index++;
            preparedStatement.setLong(index, news.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean removeNews(News news) {
        return false;
    }

    @Override
    public NewsBlock upDate(NewsBlock newsBlock) {
        return null;
    }

    @Override
    public NewsBlock findByIDNewsBlock(Long idNewsBlock) {
        NewsBlock newsBlock = new NewsBlock();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NEWS_BLOCk);
            int index = 1;
            preparedStatement.setLong(index, idNewsBlock);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            newsBlock.setName(resultSet.getString(RESULT_NAME));
            preparedStatement = connection.prepareStatement(SELECT_NEWS);
            preparedStatement.setLong(index, idNewsBlock);
            resultSet = preparedStatement.executeQuery();
            found = resultSet.next();
            if (!found) return null;
            News news ;
            while (found) {
                news = new News();
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
