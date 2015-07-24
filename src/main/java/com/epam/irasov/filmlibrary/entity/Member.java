package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public class Member extends NamedEntity{
    private String patronymic;
    private String surname;
    private LocalDate birthDate;
    private String photo;

    public Member() {
    }

    public Member(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo) {
        super(id, name);
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
}
