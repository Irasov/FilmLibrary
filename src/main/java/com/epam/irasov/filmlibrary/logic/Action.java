package com.epam.irasov.filmlibrary.logic;

import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.User;

import java.util.List;

public class Action {
    public static final int PERCENT = 100;

    public static int countRating(int usersNumber, int votersNumber){
        return (votersNumber*PERCENT)/usersNumber;
    }

    public static int votersNumber(List<FilmMember> filmMembers){
        return filmMembers.size();
    }

    public static int usersNumber(List<User> users){
        return users.size();
    }
}
