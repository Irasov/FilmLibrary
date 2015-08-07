package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcFilmDao implements FilmDao {
    private final static String RESULT_ID = "id";
    private final static String RESULT_PREMIER = "premier";
    private final static String RESULT_NAME = "name";
    private static final String RESULT_TAG_LINE = "tagline";
    private static final String RESULT_AGE_RESTRICTION = "age_restriction";
    private final static String RESULT_COUNTRY = "country";
    private final static String RESULT_LAST_NAME = "last_name";
    private final static String RESULT_SECOND_NAME = "second_name";
    private final static String RESULT_PATRONYMIC = "patronymic";
    private final static String RESULT_ROLE = "role";
    private final static String FIND_ALL_FILMS = "SELECT * FROM FILM";
    private final static String SAVE_FILM = "INSERT INTO FILM(ID, NAME, TAGLINE, AGE_RESTRICTION, )";
    private final static String FIND_BY_ID = "SELECT ID, NAME, COUNTRY, DATE FROM MOVIE WHERE ID = ?";
    private final static String FIND_BY_ID_MEMBER = "SELECT ID,SECOND_NAME,LAST_NAME,PATRONYMIC,DATE,ROLE FROM MEMBER WHERE ID=ANY(SELECT ID_MEMBER FROM MOVIE_MEMBER WHERE ID_MOVIE=?)";
    private final static String INSERT_MOVIE = "INSERT INTO MOVIE(ID, NAME, COUNTRY, DATE) VALUES(?,?,?,?)";
    private final static String INSERT_MEMBER = "INSERT INTO MEMBER(SECOND_NAME, LAST_NAME, PATRONYMIC, DATE, ROLE) VALUES(?,?,?,?,?)";
    private final static String INSERT_MOVIE_MEMBER = "INSERT INTO MOVIE_MEMBER(ID_MOVIE, ID_MEMBER) VALUES(?,?)";
    private final static String DELETE_MOVIE = "DELETE FROM MOVIE WHERE DATE>?";
    private final static String DELETE_MOVIE_MEMBER = "DELETE FROM MOVIE_MEMBER WHERE ID_MOVIE=ANY(SELECT ID FROM MOVIE WHERE DATE>?)";
    private final static String FIND_BY_COUNT_MOVIE_MEMBER = "SELECT ID,SECOND_NAME,LAST_NAME,PATRONYMIC,DATE,ROLE FROM MEMBER WHERE ROLE=? AND ID=ANY(SELECT ID_MEMBER FROM MOVIE_MEMBER GROUP BY ID_MEMBER HAVING COUNT(ID_MEMBER)=?)";

    private final Connection connection;

    public JdbcFilmDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Film findById(Long id) {
        return null;
    }

    @Override
    public Film save(Film film) {

        return null;
    }

    @Override
    public boolean remove(Film film) {
        return false;
    }

    @Override
    public Film upDate(Film film) {
        return null;
    }

    @Override
    public List<Film> selectFilms() {
        List<Film> films = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FILMS);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Film film;
            while (found) {
                film = new Film();
                film.setId(resultSet.getLong(RESULT_ID));
                film.setName(resultSet.getString(RESULT_NAME));
            //   film.setTagLine(resultSet.getString(RESULT_TAG_LINE));
               // film.setAgeRestriction(resultSet.getInt(RESULT_AGE_RESTRICTION));
                film.setPremiere(LocalDate.parse(resultSet.getDate(RESULT_PREMIER).toString(), ofPattern("yyyy-MM-dd")));
                films.add(film);
                found = resultSet.next();
            }
            return films;
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
}
