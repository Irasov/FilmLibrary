package com.epam.irasov.filmlibrary.validator;

public class Validator {
    public static String isLoginValid(String login){
        if((login.length()<4)||(login.length()>15)){
            return "error.login.too_long";
        }
        if(!login.matches("[a-z0-9]+")){
            return "error.login.incorrect_characters";
        }
        return null;
    }
}
