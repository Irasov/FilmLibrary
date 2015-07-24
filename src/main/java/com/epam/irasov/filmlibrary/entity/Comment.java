package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public class Comment extends NamedEntity {
    private LocalDate date;
    private String text;

    public Comment(){

    }

    public Comment(Long id, String name, LocalDate date, String text) {
        super(id, name);
        this.date = date;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
