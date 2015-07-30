package com.epam.irasov.filmlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class InformationBlock extends NamedEntity {
    private List<Film> films;
    private List<News> news;

    public InformationBlock() {
        films = new ArrayList<>();
        news = new ArrayList<>();
    }

    public InformationBlock(Long id, String name) {
        super(id, name);
        films = new ArrayList<>();
        news = new ArrayList<>();
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film){
        films.add(film);
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
        if (!(o instanceof InformationBlock)) return false;
        if (!super.equals(o)) return false;
        InformationBlock that = (InformationBlock) o;
        return !(films != null ? !films.equals(that.films) : that.films != null) && !(news != null ? !news.equals(that.news) : that.news != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (films != null ? films.hashCode() : 0);
        result = 31 * result + (news != null ? news.hashCode() : 0);
        return result;
    }
}
