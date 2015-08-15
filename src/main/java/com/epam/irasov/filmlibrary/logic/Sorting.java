package com.epam.irasov.filmlibrary.logic;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.NamedEntity;
import java.util.Collections;
import java.util.List;

public class Sorting {
    public static final String CONST_SORT_NAME = "name";

    public static void sortFilm(List<Film> a, String variant) {
        if (variant.equals(CONST_SORT_NAME)) {
            Collections.sort(a, NamedEntity.NAME_ORDER);
        }
    }

    public static void sortFilmMember(List<FilmMember> a, String variant) {
        if (variant.equals(CONST_SORT_NAME)) {
            Collections.sort(a, NamedEntity.NAME_ORDER);
        }
    }
}
