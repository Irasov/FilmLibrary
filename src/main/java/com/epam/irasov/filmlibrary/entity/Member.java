package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public abstract class Member extends NamedEntity {
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

    public Member() {
    }

    public Member(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, Type type) {
        super(id, name);
        this.patronymic = patronymic;
        this.surname = surname;
        this.birthDate = birthDate;
        this.photo = photo;
        this.type = type;
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
        if (!(o instanceof Member)) return false;
        if (!super.equals(o)) return false;
        Member member = (Member) o;
        return !(patronymic != null ? !patronymic.equals(member.patronymic) : member.patronymic != null) && !(surname != null ? !surname.equals(member.surname) : member.surname != null) && !(birthDate != null ? !birthDate.equals(member.birthDate) : member.birthDate != null) && !(photo != null ? !photo.equals(member.photo) : member.photo != null) && !(type != null ? !type.equals(member.type) : member.type != null);

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
