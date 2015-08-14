package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {

    private final Connection connection;

    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FilmDao newFilmDao() {
        return new JdbcFilmDao(connection);
    }

    @Override
    public SystemMemberDao newSystemMemberDao() {
        return new JdbcSystemMemberDao(connection);
    }

    @Override
    public ReviewDao newReviewDao() {
        return new JdbcReviewDao(connection);
    }

    @Override
    public NewsDao newNewsDao() {
        return new JdbcNewsDao(connection);
    }

    @Override
    public FilmMemberDao newFilmMemberDao() {
        return new JdbcFilmMemberDao(connection);
    }

    @Override
    public NewsBlockDao newNewsBlockDao() {
        return new JdbcNewsBlockDao(connection);
    }

    @Override
    public FilmBlockDao newFilmBlockDao() {
        return new JdbcFilmBlockDao(connection);
    }

    @Override
    public RatingDao newRatingDao() {
        return new JdbcRatingDao(connection);
    }

    @Override
    public void beginTx() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void endTx() {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollbackTx() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
