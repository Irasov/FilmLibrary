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
    private final static String SELECT_FILMS = "SELECT * FROM FILM";
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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILMS);
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



/*
    public Movie save(Movie movie) {
        return null;
    }

    @Override
    public Movie merge(Movie movie) {
        return null;
    }

    public List<Movie.Member> selectMember(int countMovie) {
        List<Movie.Member> members = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_COUNT_MOVIE_MEMBER);
            int index = 1;
            preparedStatement.setString(index, "actor");
            index++;public Movie findById(Long id) {
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
                    int index = 1;
                    preparedStatement.setLong(index, id);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    boolean found = resultSet.next();
                    if (!found) return null;
                    Movie movie = new Movie();
                    movie.setId(resultSet.getLong(RESULT_ID));
                    movie.setName(resultSet.getString(RESULT_NAME));
                    movie.setRelease(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
                    movie.setCountry(resultSet.getString(RESULT_COUNTRY));

                    preparedStatement = connection.prepareStatement(FIND_BY_ID_MEMBER);
                    preparedStatement.setLong(index, id);
                    resultSet = preparedStatement.executeQuery();
                    found = resultSet.next();
                    if (!found) return null;
                    Movie.Member member;
                    while (found) {
                        member = new Movie.Member();
                        member.setId(resultSet.getLong(RESULT_ID));
                        member.setName(resultSet.getString(RESULT_LAST_NAME));
                        member.setSecondName(resultSet.getString(RESULT_SECOND_NAME));
                        member.setPatronymic(resultSet.getString(RESULT_PATRONYMIC));
                        member.setDate(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
                        member.setMemberRole(resultSet.getString(RESULT_ROLE));
                        movie.addMember(member);
                        found = resultSet.next();
                    }
                    return movie;
                } catch (SQLException | NullPointerException e) {
                    throw new DaoException(e);
                }
            }

            public void update(Movie movie) {
            }
            preparedStatement.setLong(index, countMovie);
            ResultSet resultSet = preparedStatement.executeQuery();
            Boolean found = resultSet.next();
            if (!found) return null;
            Movie.Member member;
            while (found) {
                member = new Movie.Member();
                member.setId(resultSet.getLong(RESULT_ID));
                member.setName(resultSet.getString(RESULT_LAST_NAME));
                member.setSecondName(resultSet.getString(RESULT_SECOND_NAME));
                member.setPatronymic(resultSet.getString(RESULT_PATRONYMIC));
                member.setDate(LocalDate.parse(resultSet.getDate(RESULT_DATE).toString(), ofPattern("yyyy-MM-dd")));
                member.setMemberRole(resultSet.getString(RESULT_ROLE));
                members.add(member);
                found = resultSet.next();
            }
            return members;

        }catch (SQLException e){
            throw new DaoException(e);
        }
    }

    public Movie insert(Movie movie) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIE);
            setInsertMovie(preparedStatement, movie.getId(), movie.getName(), movie.getCountry(), movie.getRelease());
            for (Movie.Member member : movie.getMembers()) {
                preparedStatement = connection.prepareStatement(INSERT_MEMBER);
                setInsertMember(preparedStatement, member.getSecondName(), member.getName(), member.getPatronymic(), member.getDate(), member.getMemberRole());
                preparedStatement = connection.prepareStatement(INSERT_MOVIE_MEMBER);
                setInsertMovieMember(preparedStatement, movie.getId(), member.getId());
            }
            return movie;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Movie.Member insertMember(Movie.Member member) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBER);
            setInsertMember(preparedStatement, member.getSecondName(), member.getName(), member.getPatronymic(), member.getDate(), member.getMemberRole());
            return member;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    static void setInsertMovieMember(PreparedStatement preparedStatement, Long idMovie, Long idMember) throws SQLException {
        int index = 1;
        preparedStatement.setLong(index, idMovie);
        index++;
        preparedStatement.setLong(index, idMember);
        preparedStatement.executeUpdate();
    }

    static void setInsertMember(PreparedStatement preparedStatement, String secondName, String lastName, String patronymic, LocalDate date, String role) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, secondName);
        index++;
        preparedStatement.setString(index, lastName);
        index++;
        preparedStatement.setString(index, patronymic);
        index++;
        preparedStatement.setDate(index, Date.valueOf(date));
        index++;
        preparedStatement.setString(index, role);
        preparedStatement.executeUpdate();
    }

    static void setInsertMovie(PreparedStatement preparedStatement, Long id, String name, String country, LocalDate release) throws SQLException {
        int index = 1;
        preparedStatement.setLong(index, id);
        index++;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, country);
        index++;
        preparedStatement.setDate(index, Date.valueOf(release));
        preparedStatement.executeUpdate();
    }

    public boolean remove(Movie movie) {
        return false;
    }

    public void removeByDate(LocalDate release) {
        try {
            int index = 1;
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOVIE_MEMBER);
            preparedStatement.setDate(index, Date.valueOf(release));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(DELETE_MOVIE);
            preparedStatement.setDate(index, Date.valueOf(release));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }*/
}
