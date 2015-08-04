package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;

import java.sql.Connection;

public class JdbcNewsBlockDao implements NewsBlockDao{
    private final Connection connection;

    public JdbcNewsBlockDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public NewsBlock save(NewsBlock newsBlock) {
        return null;
    }

    @Override
    public void addNews(News news) {

    }

    @Override
    public boolean removeNews(News news) {
        return false;
    }

    @Override
    public boolean remove(NewsBlock newsBlock) {
        return false;
    }

    @Override
    public NewsBlock upDate(NewsBlock newsBlock) {
        return null;
    }
}
