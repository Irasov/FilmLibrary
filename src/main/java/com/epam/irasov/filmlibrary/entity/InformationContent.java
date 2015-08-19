package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.Comparator;

public abstract class InformationContent extends NamedEntity {
    public static final Comparator<InformationContent> NAME_ORDER = new DateComparator();
    private LocalDate date;
    private String text;

    public InformationContent() {

    }

    public InformationContent(Long id, String name, LocalDate date, String text) {
        super(id, name);
        this.date = date;
        this.text = text;
    }

    public static class DateComparator implements Comparator<InformationContent> {
        public int compare(InformationContent o1, InformationContent o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
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
