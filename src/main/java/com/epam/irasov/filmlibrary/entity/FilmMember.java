package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmMember extends Member {
    private List<Type> types;

    public FilmMember() {
        super();
        types = new ArrayList<>();
    }

    public FilmMember(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo) {
        super(id, name, patronymic, surname, birthDate, photo);
        types = new ArrayList<>();
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public void addType(Type type){
        types.add(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmMember)) return false;
        if (!super.equals(o)) return false;
        FilmMember that = (FilmMember) o;
        return !(types != null ? !types.equals(that.types) : that.types != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (types != null ? types.hashCode() : 0);
        return result;
    }
}
