package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcAdminDao implements AdminDao {

    private final Connection connection;

    public JdbcAdminDao(Connection connection) {
        this.connection = connection;
    }
}
