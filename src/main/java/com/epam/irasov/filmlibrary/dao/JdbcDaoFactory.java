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
    public UserDao newUserDao() {
        return new JdbcUserDao(connection);
    }

    @Override
    public AdminDao newAdminDao() {
        return new JdbcAdminDao(connection);
    }

    @Override
    public GenreDao newGenreDao() {
        return new JdbcGenreDao(connection);
    }

    @Override
    public HonorDao newHonorDao() {
        return new JdbcHonorDao(connection);
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
