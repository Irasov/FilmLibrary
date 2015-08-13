package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.Rating;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcFilmDao implements FilmDao {
    private final static String RESULT_ID = "id";
    private final static String RESULT_PREMIERE = "premiere";
    private final static String RESULT_NAME = "name";
    private static final String RESULT_TAG_LINE = "tagline";
    private static final String RESULT_AGE_RESTRICTION = "age_restriction";
    private static final String RESULT_ID_RATING = "id_rating";
    private final static String RESULT_LAST_NAME = "last_name";
    private final static String RESULT_SECOND_NAME = "second_name";
    private final static String RESULT_PATRONYMIC = "patronymic";
    private final static String RESULT_ROLE = "role";
    private static final String RESULT_COVER = "cover";
    private static final String RESULT_DURATION = "duration";
    private static final String RESULT_RATING = "rating";
    private static final String RESULT_DESCRIPTION = "description";
    private final static String FIND_BY_ID_FILMS = "SELECT * FROM FILM WHERE ID=?";
    private final static String FIND_ALL_FILMS = "SELECT * FROM FILM";
    private final static String SAVE_FILM = "INSERT INTO FILM(NAME, TAGLINE, AGE_RESTRICTION, DURATION, COVER, DESCRIPTION, ID_RATING, PREMIERE )VALUES(?,?,?,?,?,?,?,?)";
    private final static String FIND_BY_ID = "SELECT* FROM FILM WHERE ID = ?";
    private final static String FIND_RATING = "SELECT * FROM RATING WHERE ID=?";
    private final static String UPDATE_FILM = "UPDATE FILM SET NAME = ?, TAGLINE = ?, AGE_RESTRICTION=?, DURATION =  ?, DESCRIPTION = ?, PREMIERE=?  WHERE ID=?";
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            int index = 1;
            preparedStatement.setLong(index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Film film = new Film();;
            film.setId(resultSet.getLong(RESULT_ID));
            film.setName(resultSet.getString(RESULT_NAME));
            film.setTagLine(resultSet.getString(RESULT_TAG_LINE));
            film.setAgeRestriction(resultSet.getInt(RESULT_AGE_RESTRICTION));
            film.setDuration(resultSet.getInt(RESULT_DURATION));
            film.setCover(resultSet.getString(RESULT_COVER));
            film.setDescription(resultSet.getString(RESULT_DESCRIPTION));
            film.setRating(findRating(resultSet.getLong(RESULT_ID_RATING)));
            film.setPremiere(LocalDate.parse(resultSet.getDate(RESULT_PREMIERE).toString(), ofPattern("yyyy-MM-dd")));
            return film;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Film save(Film film) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FILM);
            setInsertFilm(preparedStatement, film.getName(), film.getTagLine(), film.getAgeRestriction(), film.getDuration(), film.getCover(), film.getDescription(), film.getRating().getId(), film.getPremiere());
            return film;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setInsertFilm(PreparedStatement preparedStatement, String name, String tagLine, int ageRestriction, int duration, String cover, String description, Long id, LocalDate premiere) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, tagLine);
        index++;
        preparedStatement.setInt(index, ageRestriction);
        index++;
        preparedStatement.setInt(index, duration);
        index++;
        preparedStatement.setString(index, cover);
        index++;
        preparedStatement.setString(index, description);
        index++;
        preparedStatement.setLong(index, id);
        index++;
        preparedStatement.setDate(index, Date.valueOf(premiere));
        preparedStatement.executeUpdate();
    }


    @Override
    public boolean remove(Film film) {
        return false;
    }

    @Override
    public void upDate(Film film) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILM);
            setUpdateFilm(preparedStatement, film.getName(), film.getTagLine(), film.getAgeRestriction(), film.getDuration(), film.getDescription(), film.getPremiere(), film.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setUpdateFilm(PreparedStatement preparedStatement, String name, String tagLine, int ageRestriction, int duration, String description, LocalDate premiere, Long id) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, tagLine);
        index++;
        preparedStatement.setInt(index, ageRestriction);
        index++;
        preparedStatement.setInt(index, duration);
        index++;
        preparedStatement.setString(index, description);
        index++;
        preparedStatement.setDate(index, Date.valueOf(premiere));
        index++;
        preparedStatement.setLong(index, id);
        preparedStatement.executeUpdate();
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
                film.setTagLine(resultSet.getString(RESULT_TAG_LINE));
                film.setAgeRestriction(resultSet.getInt(RESULT_AGE_RESTRICTION));
                film.setDuration(resultSet.getInt(RESULT_DURATION));
                film.setCover(resultSet.getString(RESULT_COVER));
                film.setRating(findRating(resultSet.getLong(RESULT_ID_RATING)));
                film.setDescription(resultSet.getString(RESULT_DESCRIPTION));
                film.setPremiere(LocalDate.parse(resultSet.getDate(RESULT_PREMIERE).toString(), ofPattern("yyyy-MM-dd")));
                films.add(film);
                found = resultSet.next();
            }
            return films;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Rating findRating(Long idRating) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_RATING);
            int index = 1;
            preparedStatement.setLong(index, idRating);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Rating rating = new Rating();
            rating.setId(resultSet.getLong(RESULT_ID));
            rating.setName(resultSet.getString(RESULT_NAME));
            return rating;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
