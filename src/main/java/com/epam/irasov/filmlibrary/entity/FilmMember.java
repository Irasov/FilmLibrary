package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmMember extends Member {
    private List<Honor> honors;

    public FilmMember() {
        super();
        honors = new ArrayList<>();
    }

    public FilmMember(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, Type type) {
        super(id, name, patronymic, surname, birthDate, photo, type);
        honors = new ArrayList<>();
    }

    public List<Honor> getHonors() {
        return honors;
    }

    public void setHonors(List<Honor> honors) {
        this.honors = honors;
    }

    public void addHonor(Honor honor) {
        honors.add(honor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmMember)) return false;
        if (!super.equals(o)) return false;
        FilmMember that = (FilmMember) o;
        return !(honors != null ? !honors.equals(that.honors) : that.honors != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (honors != null ? honors.hashCode() : 0);
        return result;
    }
}
