package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcNewsDao implements NewsDao {
    private final Connection connection;

    public JdbcNewsDao(Connection connection) {
        this.connection = connection;
    }
}
