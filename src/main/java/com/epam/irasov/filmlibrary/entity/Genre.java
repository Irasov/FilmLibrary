package com.epam.irasov.filmlibrary.entity;

public class Genre extends NamedEntity {
    private String description;

    public Genre() {
    }

    public Genre(Long id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        if (!super.equals(o)) return false;
        Genre genre = (Genre) o;
        return !(description != null ? !description.equals(genre.description) : genre.description != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
