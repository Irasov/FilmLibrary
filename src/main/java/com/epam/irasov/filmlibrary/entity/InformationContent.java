package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public abstract class InformationContent extends NamedEntity {
    private LocalDate date;
    private String text;

    public InformationContent() {

    }

    public InformationContent(Long id, String name, LocalDate date, String text) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InformationContent)) return false;
        if (!super.equals(o)) return false;
        InformationContent that = (InformationContent) o;
        return !(date != null ? !date.equals(that.date) : that.date != null) && !(text != null ? !text.equals(that.text) : that.text != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
