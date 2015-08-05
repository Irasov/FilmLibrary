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

    public static String isPasswordValid(String login){
        if((login.length()<7)||(login.length()>15)){
            return "error.password.too_long";
        }
        if(!login.matches("[A-z0-9]+")){
            return "error.password.incorrect_characters";
        }
        return null;
    }
}
