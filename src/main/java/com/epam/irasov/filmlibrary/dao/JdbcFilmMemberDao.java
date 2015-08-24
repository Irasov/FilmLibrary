package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.FilmMember;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.*;
import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcFilmMemberDao implements FilmMemberDao {
    private static final String RESULT_ID_TYPE = "id_type";
    private final static String SAVE_FILM_MEMBER_TYPE = "INSERT INTO FILM_MEMBER_TYPE(ID,NAME) VALUES(?,?)";
    private static final String FIND_FILM_MEMBER_TYPE = "SELECT ID FROM FILM_MEMBER_TYPE";
    private static final String SELECT_ALL_TYPES = "SELECT * FROM FILM_MEMBER_TYPE";
    private final static String INSERT_FILM_MEMBER = "INSERT INTO FILM_MEMBER(NAME, PATRONYMIC, SURNAME, BIRTH_DATE, PHOTO,ID_TYPE) VALUES(?,?,?,?,?,?)";
    private static final String EMPTY_TABLE = "SELECT ID FROM FILM_MEMBER";
    private static final String FIND_ALL_FILMS = "SELECT * FROM FILM_MEMBER";
    private static final String FIND_BY_ID_TYPE = "SELECT * FROM FILM_MEMBER_TYPE WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM FILM_MEMBER WHERE ID=?";
    private static final String UPDATE_FILM_MEMBER = "UPDATE FILM_MEMBER SET NAME = ?, PATRONYMIC = ?, SURNAME = ?, BIRTH_DATE = ? , PHOTO = ? WHERE ID=?";
    private static final String DELETE_FILM_MEMBER = "DELETE FROM FILM_MEMBER WHERE id=?";
    private static final String DELETE_FROM_FILMS = "DELETE FROM FILM_FILM_MEMBER WHERE ID_FILM_MEMBER=?";
    private static final String FIND_BY_NAME = "SELECT * FROM FILM_MEMBER WHERE SURNAME=?";

    private final Connection connection;

    public JdbcFilmMemberDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public FilmMember.Type saveType(FilmMember.Type type) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FILM_MEMBER_TYPE);
            int index = 1;
            preparedStatement.setLong(index, type.getId());
            index++;
            preparedStatement.setString(index, type.getName());
            preparedStatement.executeUpdate();
            return type;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findType() {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FILM_MEMBER_TYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                count++;
                found = resultSet.next();
            }
            return count;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<FilmMember.Type> select() {
        List<FilmMember.Type> types = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TYPES);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                types.add(new FilmMember.Type(resultSet.getLong(RESULT_ID), resultSet.getString(RESULT_NAME)));
                found = resultSet.next();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return types;
    }

    @Override
    public FilmMember saveFilmMember(FilmMember member) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILM_MEMBER);
            System.out.println("SAVE" + member.getPhoto());
            setInsertFilmMember(preparedStatement, member.getName(), member.getPatronymic(), member.getSurname(), member.getBirthDate(), member.getType().getId(), member.getPhoto());
            return member;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setInsertFilmMember(PreparedStatement preparedStatement, String name, String patronymic, String surname, LocalDate birthDate, Long id, String photo) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, patronymic);
        index++;
        preparedStatement.setString(index, surname);
        index++;
        preparedStatement.setDate(index, Date.valueOf(birthDate));
        index++;
        preparedStatement.setString(index, photo);
        index++;
        preparedStatement.setLong(index, id);
        preparedStatement.executeUpdate();
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
    public List<FilmMember> selectFilmMember() {
        List<FilmMember> filmMembers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FILMS);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            FilmMember filmMember;
            while (found) {
                filmMember = new FilmMember();
                filmMember.setId(resultSet.getLong(RESULT_ID));
                filmMember.setName(resultSet.getString(RESULT_NAME));
                filmMember.setPatronymic(resultSet.getString(RESULT_PATRONYMIC));
                filmMember.setSurname(resultSet.getString(RESULT_SURNAME));
                filmMember.setPhoto(resultSet.getString(RESULT_PHOTO));
                filmMember.setBirthDate(LocalDate.parse(resultSet.getDate(RESULT_BIRTH_DATE).toString(), ofPattern("yyyy-MM-dd")));
                filmMember.setType(findType(resultSet.getLong(RESULT_ID_TYPE)));
                filmMembers.add(filmMember);
                found = resultSet.next();
            }
            return filmMembers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private FilmMember.Type findType(long idType) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_TYPE);
            int index = 1;
            preparedStatement.setLong(index, idType);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            FilmMember.Type type = new FilmMember.Type();
            type.setId(resultSet.getLong(RESULT_ID));
            type.setName(resultSet.getString(RESULT_NAME));
            return type;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FilmMember findById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            int index = 1;
            preparedStatement.setLong(index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            FilmMember filmMember = new FilmMember();
            filmMember.setId(resultSet.getLong(RESULT_ID));
            filmMember.setName(resultSet.getString(RESULT_NAME));
            filmMember.setPatronymic(resultSet.getString(RESULT_PATRONYMIC));
            filmMember.setSurname(resultSet.getString(RESULT_SURNAME));
            filmMember.setBirthDate(LocalDate.parse(resultSet.getDate(RESULT_BIRTH_DATE).toString(), ofPattern("yyyy-MM-dd")));
            filmMember.setType(findType(resultSet.getLong(RESULT_ID_TYPE)));
            filmMember.setPhoto(resultSet.getString(RESULT_PHOTO));
            System.out.println("find" +filmMember.getPhoto());
            return filmMember;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void upDate(FilmMember filmMember) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILM_MEMBER);
            setUpdateFilmMember(preparedStatement, filmMember.getName(), filmMember.getPatronymic(), filmMember.getSurname(), filmMember.getBirthDate(), filmMember.getPhoto(), filmMember.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setUpdateFilmMember(PreparedStatement preparedStatement, String name, String patronymic, String surname, LocalDate birthDate, String photo, Long id) throws SQLException {
        int index = 1;
        System.out.println("UPDATE"+photo);
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, patronymic);
        index++;
        preparedStatement.setString(index, surname);
        index++;
        preparedStatement.setDate(index, Date.valueOf(birthDate));
        index++;
        preparedStatement.setString(index, photo);
        index++;
        preparedStatement.setLong(index, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public Long remove(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILM_MEMBER);
            int index = 1;
            preparedStatement.setLong(index, id);
            preparedStatement.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeFromFilms(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_FILMS);
            int index = 1;
            preparedStatement.setLong(index, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public FilmMember findBySurname(String surname) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            int index = 1;
            preparedStatement.setString(index, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            FilmMember filmMember = new FilmMember();
            filmMember.setId(resultSet.getLong(RESULT_ID));
            filmMember.setName(resultSet.getString(RESULT_NAME));
            filmMember.setPatronymic(resultSet.getString(RESULT_PATRONYMIC));
            filmMember.setSurname(resultSet.getString(RESULT_SURNAME));
            filmMember.setBirthDate(LocalDate.parse(resultSet.getDate(RESULT_BIRTH_DATE).toString(), ofPattern("yyyy-MM-dd")));
            filmMember.setType(findType(resultSet.getLong(RESULT_ID_TYPE)));
            filmMember.setPhoto(resultSet.getString(RESULT_PHOTO));
            System.out.println("find" +filmMember.getPhoto());
            return filmMember;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}