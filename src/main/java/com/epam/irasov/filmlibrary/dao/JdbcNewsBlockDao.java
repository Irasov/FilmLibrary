package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcNewsBlockDao implements NewsBlockDao{
    private final Connection connection;

    public JdbcNewsBlockDao(Connection connection) {
        this.connection = connection;
    }
}
