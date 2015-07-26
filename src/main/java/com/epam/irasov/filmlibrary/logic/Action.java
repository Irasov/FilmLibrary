package com.epam.irasov.filmlibrary.logic;

public class Action {
    public static final int PERCENT = 100;

    public static int countRating(int usersNumber,int votersNumber){
        return (votersNumber*PERCENT)/usersNumber;
    }
}
