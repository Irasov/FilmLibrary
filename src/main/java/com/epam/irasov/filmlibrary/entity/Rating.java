package com.epam.irasov.filmlibrary.entity;

public class Rating extends NamedEntity {
    private int votes;

    public Rating() {
    }

    public Rating(Long id, String name, int votes) {
        super(id, name);
        this.votes = votes;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        if (!super.equals(o)) return false;
        Rating rating = (Rating) o;
        return votes == rating.votes;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + votes;
        return result;
    }
}
