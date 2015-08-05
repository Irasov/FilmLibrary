package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.bd.ConnectionPool;

import java.sql.Connection;

public abstract class DaoFactory {

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public static DaoFactory getInstance() {
        Connection connection = pool.takeConnection();
        return new JdbcDaoFactory(connection);
    }

    public abstract FilmDao newFilmDao();

    public abstract SystemMemberDao newSystemMember();
    public abstract SystemMemberDao newSystemMemberDao();

    public abstract GenreDao newGenreDao();

    public abstract HonorDao newHonorDao();

    public abstract ReviewDao newReviewDao();

    public abstract NewsDao newNewsDao();

    public abstract FilmMemberDao newFilmMemberDao();

    public abstract NewsBlockDao newNewsBlockDao();

    public abstract FilmBlockDao newFilmBlockDao();

    public abstract void beginTx();

    public abstract void endTx();

    public abstract void rollbackTx();

    public abstract void close();
}
