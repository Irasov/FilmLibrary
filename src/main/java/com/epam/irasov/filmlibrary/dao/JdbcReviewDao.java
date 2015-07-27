package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcReviewDao implements ReviewDao {

    private final Connection connection;

    public JdbcReviewDao(Connection connection) {
        this.connection = connection;
    }
}
