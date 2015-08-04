package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;

import java.util.List;

public interface NewsBlockDao {
    NewsBlock save(NewsBlock newsBlock);
    void addNews (News news);
    boolean removeNews(News news);
    boolean remove(NewsBlock newsBlock);
    NewsBlock upDate(NewsBlock newsBlock);
    NewsBlock selectNews(Long idNewsBlock);
}
