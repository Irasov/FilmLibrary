package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcGenreDao implements GenreDao {

    private final Connection connection;

    public JdbcGenreDao(Connection connection) {
        this.connection = connection;
    }
}
