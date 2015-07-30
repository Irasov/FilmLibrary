package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcFilmBlockDao implements FilmBlockDao{
    private final Connection connection;

    public JdbcFilmBlockDao(Connection connection) {
        this.connection = connection;
    }
}
