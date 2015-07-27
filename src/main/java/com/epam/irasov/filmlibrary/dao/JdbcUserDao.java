package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcUserDao implements UserDao {

    private final Connection connection;

    public JdbcUserDao(Connection connection) {
        this.connection = connection;
    }
}
