package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.News;

public interface NewsDao {
    News save (News news);
    News findByIDNews(Long id);
}
