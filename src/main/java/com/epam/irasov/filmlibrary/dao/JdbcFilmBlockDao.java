package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmBlock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.*;
import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcFilmBlockDao implements FilmBlockDao {
    private final static String RESULT_PREMIERE = "premiere";
    private final static String RESULT_COVER = "cover";
    private final static Long FILM_BLOCK_ID = 2l;
    private static final String INSERT_FILM_BLOCK = "INSERT INTO INFORMATION_BLOCK (ID,NAME) VALUES (?,?)";
    private static final String ADD_FILMS = "INSERT INTO INFORMATION_BLOCK_FILM(ID_INFORMATION_BLOCK, ID_FILM) VALUES (?,?)";
    private static final String SELECT_FILMS = "SELECT ID,NAME, COVER, PREMIERE FROM FILM WHERE ID=ANY(SELECT ID_FILM FROM INFORMATION_BLOCK_FILM WHERE ID_INFORMATION_BLOCK=?)";
    private static final String EMPTY_TABLE = "SELECT * FROM INFORMATION_BLOCK_FILM";
    private static final String SELECT_FILM_BLOCK = "SELECT NAME FROM INFORMATION_BLOCK WHERE ID=?";
    private static final String DELETE_FILM_FROM_FILMS_BLOCK = "DELETE FROM INFORMATION_BLOCK_FILM WHERE ID_FILM=?";
    private static final String FIND_FILM = "SELECT ID FROM INFORMATION_BLOCK_FILM WHERE ID_FILM=?";

    private final Connection connection;

    public JdbcFilmBlockDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean emptyTable() {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(EMPTY_TABLE);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                count++;
                found = resultSet.next();
            }
            return count == 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FilmBlock save(FilmBlock filmBlock) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILM_BLOCK);
            int index = 1;
            preparedStatement.setLong(index, filmBlock.getId());
            index++;
            preparedStatement.setString(index, filmBlock.getName());
            preparedStatement.executeUpdate();
            return filmBlock;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addFilm(Long idFilm) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_FILMS);
            int index = 1;
            preparedStatement.setLong(index,FILM_BLOCK_ID);
            index++;
            preparedStatement.setLong(index, idFilm);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean removeNews(Film film) {
        return false;
    }

    @Override
    public FilmBlock upDate(FilmBlock filmBlock) {
        return null;
    }

    @Override
    public FilmBlock findByIDFilmBlock(Long idFilmBlock) {
        FilmBlock filmBlock = new FilmBlock();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILM_BLOCK);
            int index = 1;
            preparedStatement.setLong(index, idFilmBlock);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            filmBlock.setName(resultSet.getString(RESULT_NAME));
            preparedStatement = connection.prepareStatement(SELECT_FILMS);
            preparedStatement.setLong(index, idFilmBlock);
            resultSet = preparedStatement.executeQuery();
            found = resultSet.next();
            if (!found) return null;
            Film film;
            while (found) {
                film = new Film();
                film.setId(resultSet.getLong(RESULT_ID));
                film.setName(resultSet.getString(RESULT_NAME));
                film.setPremiere(LocalDate.parse(resultSet.getDate(RESULT_PREMIERE).toString(), ofPattern("yyyy-MM-dd")));
                film.setCover(resultSet.getString(RESULT_COVER));
                filmBlock.addFilm(film);
                found = resultSet.next();
            }
            return filmBlock;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteFilm(Long idFilm) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILM_FROM_FILMS_BLOCK);
            int index = 1;
            preparedStatement.setLong(index, idFilm);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean findFilm(Long id) {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FILM);
            int index = 1;
            preparedStatement.setLong(index,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                count++;
                found = resultSet.next();
            }
            return count != 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
