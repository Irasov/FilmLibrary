package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcFilmMemberDao implements FilmMemberDao{

    private final Connection connection;

    public JdbcFilmMemberDao(Connection connection) {
        this.connection = connection;
    }
}
