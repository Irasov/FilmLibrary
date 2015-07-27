package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcHonorDao implements HonorDao {

    private final Connection connection;

    public JdbcHonorDao(Connection connection) {
        this.connection = connection;
    }
}
