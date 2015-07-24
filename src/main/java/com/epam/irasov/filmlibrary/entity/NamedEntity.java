package com.epam.irasov.filmlibrary.entity;

public abstract class NamedEntity extends BaseEntity {

    private String name;

    public NamedEntity(){

    }

    public NamedEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedEntity)) return false;
        if (!super.equals(o)) return false;
        NamedEntity that = (NamedEntity) o;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString()+
                " name=" + name;
    }
}
