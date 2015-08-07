package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;

public interface NewsBlockDao {
    NewsBlock save(NewsBlock newsBlock);
    void addNews (NewsBlock newsBlock, News news);
    boolean removeNews(News news);
    NewsBlock upDate(NewsBlock newsBlock);
    NewsBlock findByIDNewsBlock(Long idNewsBlock);
}
