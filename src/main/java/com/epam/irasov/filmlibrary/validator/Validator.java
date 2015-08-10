package com.epam.irasov.filmlibrary.validator;

public class Validator {
    public static String isLoginValid(String login){
        if((login.length()<4)||(login.length()>15)){
            return "error.login.too_long";
        }
        if(!login.matches("[A-z0-9]+")){
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

    public static String isEmailValid(String email){
        if(!email.matches("\\w+@[a-zA-Z]+?\\.[a-zA-Z]{2,6}")){
            return "error.email";
        }
        return null;
    }

    public static String isTagLineValid(String tagLine){
        if((tagLine.length()<1)||(tagLine.length()>250)){
            return "error.tag.line";
        }
        return null;
    }

    public static String isRestrictionAgeValid(String restrictionAge){
        if(!restrictionAge.matches("[0-9]+")){
            return "error.restriction.age";
        }
        return null;
    }

    public static String isDescriptionValid(String description){
        if((description.length()<1)||(description.length()>450)){
            return "error.description";
        }
        return null;
    }

}
