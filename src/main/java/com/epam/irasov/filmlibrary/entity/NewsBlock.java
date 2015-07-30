package com.epam.irasov.filmlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class NewsBlock extends NamedEntity {
    private List<News> news;

    public NewsBlock() {
        news = new ArrayList<>();
    }

    public NewsBlock(Long id, String name) {
        super(id, name);
        news = new ArrayList<>();
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public void addNews(News news){
        this.news.add(news);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewsBlock)) return false;
        if (!super.equals(o)) return false;
        NewsBlock newsBlock = (NewsBlock) o;
        return !(news != null ? !news.equals(newsBlock.news) : newsBlock.news != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (news != null ? news.hashCode() : 0);
        return result;
    }
}
