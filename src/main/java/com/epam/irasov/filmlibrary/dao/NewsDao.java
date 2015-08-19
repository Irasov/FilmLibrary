package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;

import java.util.List;

public interface NewsDao {
    News save (News news);
    News findByIdNews(Long id);
    boolean emptyTable();
    List<News> selectNews();
    void upDate(News item);
    void remove(Long id);
}
