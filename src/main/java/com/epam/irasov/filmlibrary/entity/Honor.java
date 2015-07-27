package com.epam.irasov.filmlibrary.entity;

public class Honor extends NamedEntity {
    String image;

    public Honor() {
    }

    public Honor(Long id, String name, String image) {
        super(id, name);
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
        if (!(o instanceof Honor)) return false;
        if (!super.equals(o)) return false;
        Honor honor = (Honor) o;
        return !(image != null ? !image.equals(honor.image) : honor.image != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
