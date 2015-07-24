package com.epam.irasov.filmlibrary.entity;

public class User extends Member {
    private Type type;
    private String login;
    private String password;

    public static class Type extends NamedEntity {
        public Type() {
        }

        public Type(Long id, String name) {
            super(id, name);
        }
    }

    public User() {
    }

    public User(Type type, String login, String password) {
        this.type = type;
        this.login = login;
        this.password = password;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return !(type != null ? !type.equals(user.type) : user.type != null) && !(login != null ? !login.equals(user.login) : user.login != null) && !(password != null ? !password.equals(user.password) : user.password != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
