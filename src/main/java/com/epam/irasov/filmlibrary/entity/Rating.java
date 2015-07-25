package com.epam.irasov.filmlibrary.entity;

import java.util.ArrayList;
import java.util.List;

public class Rating extends NamedEntity {
    private int rating;
    private List<Member> members;

    public Rating() {
        super();
        members = new ArrayList<>();
    }

    public Rating(Long id, String name, int rating) {
        this();
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member){
        members.add(member);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        if (!super.equals(o)) return false;
        Rating rating1 = (Rating) o;
        return rating == rating1.rating && !(members != null ? !members.equals(rating1.members) : rating1.members != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + rating;
        result = 31 * result + (members != null ? members.hashCode() : 0);
        return result;
    }
}
