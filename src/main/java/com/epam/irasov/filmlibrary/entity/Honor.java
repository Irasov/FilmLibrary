package com.epam.irasov.filmlibrary.entity;

public class Honor extends NamedEntity {
    private String image;
    private String description;


    public Honor() {
    }

    public Honor(Long id, String name, String image, String description) {
        super(id, name);
        this.image = image;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        if (!(o instanceof Honor)) return false;
        if (!super.equals(o)) return false;
        Honor honor = (Honor) o;
        return !(image != null ? !image.equals(honor.image) : honor.image != null) && !(description != null ? !description.equals(honor.description) : honor.description != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
