package com.epam.irasov.filmlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class Rating extends NamedEntity {
    private List<User> members;

    public Rating() {
        super();
        members = new ArrayList<>();
    }

    public Rating(Long id, String name) {
        super(id, name);
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public void addMember(User member) {
        members.add(member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        if (!super.equals(o)) return false;
        Rating rating = (Rating) o;
        return !(members != null ? !members.equals(rating.members) : rating.members != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (members != null ? members.hashCode() : 0);
        return result;
    }
}
