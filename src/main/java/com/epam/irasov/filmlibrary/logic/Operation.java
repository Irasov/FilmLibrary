package com.epam.irasov.filmlibrary.logic;

import com.epam.irasov.filmlibrary.entity.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

public class Operation {
    public static final String SORT_NAME = "name";
    public static final String SORT_DATE = "date";
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sortFilm(List<Film> a, String variant) {
        if (variant.equals(SORT_NAME)) {
            Collections.sort(a, NamedEntity.NAME_ORDER);
        }
    }

    public static void sortFilmMember(List<FilmMember> a, String variant) {
        if (variant.equals(SORT_NAME)) {
            Collections.sort(a, NamedEntity.NAME_ORDER);
        }
    }

    public static void sortNews(List<News> a, String variant) {
        if (variant.equals(SORT_DATE)) {
            Collections.sort(a, InformationContent.NAME_ORDER);
        }
    }
}
