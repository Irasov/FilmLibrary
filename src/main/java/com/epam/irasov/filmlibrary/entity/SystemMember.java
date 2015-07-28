package com.epam.irasov.filmlibrary.entity;

import java.time.LocalDate;

public abstract class SystemMember extends Member{
    private String login;
    private String password;
    private String email;

    public SystemMember() {

    }

    public SystemMember(Long id, String name, String patronymic, String surname, LocalDate birthDate, String photo, Type type, String login, String password, String email) {
        super(id, name, patronymic, surname, birthDate, photo, type);
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemMember)) return false;
        if (!super.equals(o)) return false;
        SystemMember that = (SystemMember) o;
        return !(login != null ? !login.equals(that.login) : that.login != null) && !(password != null ? !password.equals(that.password) : that.password != null) && !(email != null ? !email.equals(that.email) : that.email != null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
