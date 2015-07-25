package com.epam.irasov.filmlibrary.entity;

public class Honor extends NamedEntity{
    String photo;

    public Honor() {
    }

    public Honor(Long id, String name, String photo) {
        super(id, name);
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Honor)) return false;
        if (!super.equals(o)) return false;
        Honor honor = (Honor) o;
        return !(photo != null ? !photo.equals(honor.photo) : honor.photo != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
