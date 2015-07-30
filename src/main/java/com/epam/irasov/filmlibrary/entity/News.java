package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public class News extends InformationContent {
    private String image;

    public News() {
    }

    public News(Long id, String name, LocalDate date, String text, String image) {
        super(id, name, date, text);
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        if (!super.equals(o)) return false;
        News news = (News) o;
        return !(image != null ? !image.equals(news.image) : news.image != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
