package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public class FilmMember extends NamedEntity {
    private String patronymic;
    private String surname;
    private LocalDate birthDate;
    private String photo;
    private Type type;

    public static class Type extends NamedEntity {
        public Type() {
        }

        public Type(Long id, String name) {
            super(id, name);
        }
    }

    public FilmMember() {

    }

    public FilmMember(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, Type type) {
        super(id, name);
        this.type = type;
        this.patronymic = patronymic;
        this.surname = surname;
        this.birthDate = birthDate;
        this.photo = photo;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmMember)) return false;
        if (!super.equals(o)) return false;
        FilmMember that = (FilmMember) o;
        return !(patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) && !(surname != null ? !surname.equals(that.surname) : that.surname != null) && !(birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) && !(photo != null ? !photo.equals(that.photo) : that.photo != null) && !(type != null ? !type.equals(that.type) : that.type != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
