package com.epam.irasov.filmlibrary.dao;

import java.sql.Connection;

public class JdbcSystemMemberDao implements SystemMemberDao {

    private final Connection connection;

    public JdbcSystemMemberDao(Connection connection) {
        this.connection = connection;
    }
}
