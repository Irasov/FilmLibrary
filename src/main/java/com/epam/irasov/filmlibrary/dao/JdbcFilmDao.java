package com.epam.irasov.filmlibrary.dao;

import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.Rating;
import com.epam.irasov.filmlibrary.entity.Review;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.epam.irasov.filmlibrary.dao.SqlQueryResult.*;
import static java.time.format.DateTimeFormatter.ofPattern;

public class JdbcFilmDao implements FilmDao {
    private final static String RESULT_ID = "id";
    private final static String RESULT_ID_FILM = "id_film";
    private final static String RESULT_PREMIERE = "premiere";
    private static final String RESULT_TAG_LINE = "tagline";
    private static final String RESULT_AGE_RESTRICTION = "age_restriction";
    private static final String RESULT_ID_RATING = "id_rating";
    private static final String RESULT_GENRE = "genre";
    private static final String RESULT_COVER = "cover";
    private static final String RESULT_DURATION = "duration";
    private static final String RESULT_DESCRIPTION = "description";
    private static final String ID_FILM_MEMBER = "id_film_member";
    private static final String RESULT_VOTES = "votes";
    private static final String ID_REVIEW = "id_review";
    private final static String FIND_ALL_FILMS = "SELECT * FROM FILM";
    private final static String SAVE_FILM = "INSERT INTO FILM(NAME, TAGLINE, GENRE, AGE_RESTRICTION, DURATION, COVER, DESCRIPTION, ID_RATING, PREMIERE )VALUES(?,?,?,?,?,?,?,?,?)";
    private final static String FIND_BY_ID = "SELECT* FROM FILM WHERE ID = ?";
    private final static String FIND_RATING = "SELECT * FROM RATING WHERE ID=?";
    private final static String UPDATE_FILM = "UPDATE FILM SET NAME = ?, TAGLINE = ?, GENRE = ?,AGE_RESTRICTION=?, DURATION =  ?, DESCRIPTION = ?, PREMIERE=?, COVER=? WHERE ID=?";
    private static final String EMPTY_TABLE = "SELECT ID FROM FILM";
    private static final String DELETE_FILM = "DELETE FROM FILM WHERE id=?";
    private static final String SELECT_ID_RATING = "SELECT ID_RATING FROM FILM WHERE ID=?";
    private static final String SAVE_FILM_FILM_MEMBER = "INSERT INTO FILM_FILM_MEMBER(ID_FILM, ID_FILM_MEMBER)VALUES(?,?)";
    private static final String FIND_FILM_FILM_MEMBER = "SELECT ID FROM FILM_FILM_MEMBER WHERE ID_FILM=?";
    private static final String FIND_FILM_MEMBER_FILM = "SELECT ID FROM FILM_FILM_MEMBER WHERE ID_FILM_MEMBER=?";
    private static final String FIND_ID_FILM_MEMBER = "SELECT ID_FILM_MEMBER FROM FILM_FILM_MEMBER WHERE ID_FILM=? ";
    private static final String DELETE_FILM_MEMBER = "DELETE FROM FILM_FILM_MEMBER WHERE ID_FILM=? AND ID_FILM_MEMBER=?";
    private static final String DELETE_LIST_MEMBER = "DELETE FROM FILM_FILM_MEMBER WHERE ID_FILM=?";
    private static final String FIND_ID_FILM = "SELECT ID_FILM FROM FILM_FILM_MEMBER WHERE ID_FILM_MEMBER=? ";
    private static final String FIND_MEMBER = "SELECT ID FROM FILM_FILM_MEMBER WHERE ID_FILM_MEMBER=? AND ID_FILM=?";
    private static final String FIND_ID_REVIEW = "SELECT ID_REVIEW FROM FILM_REVIEW WHERE ID_FILM=?";
    private static final String DELETE_REVIEW = "DELETE FROM FILM_REVIEW WHERE ID_REVIEW=?";
    private static final String FIND_BY_NAME = "SELECT* FROM FILM WHERE NAME = ?";
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
            Film film = new Film();
            film.setId(resultSet.getLong(RESULT_ID));
            film.setName(resultSet.getString(RESULT_NAME));
            film.setTagLine(resultSet.getString(RESULT_TAG_LINE));
            film.setGenre(resultSet.getString(RESULT_GENRE));
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
            setInsertFilm(preparedStatement, film.getName(), film.getTagLine(),film.getGenre(), film.getAgeRestriction(), film.getDuration(), film.getCover(), film.getDescription(), film.getRating().getId(), film.getPremiere());
            return film;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setInsertFilm(PreparedStatement preparedStatement, String name, String tagLine, String genre, int ageRestriction, int duration, String cover, String description, Long id, LocalDate premiere) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, tagLine);
        index++;
        preparedStatement.setString(index, genre);
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
    public Long remove(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID_RATING);
            int index = 1;
            preparedStatement.setLong(index, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            preparedStatement = connection.prepareStatement(DELETE_FILM);
            index = 1;
            preparedStatement.setLong(index, id);
            preparedStatement.executeUpdate();
            return resultSet.getLong(RESULT_ID_RATING);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void upDate(Film film) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILM);
            setUpdateFilm(preparedStatement, film.getName(), film.getTagLine(), film.getGenre(), film.getAgeRestriction(), film.getDuration(), film.getDescription(), film.getPremiere(), film.getCover(), film.getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private void setUpdateFilm(PreparedStatement preparedStatement, String name, String genre, String tagLine, int ageRestriction, int duration, String description, LocalDate premiere, String cover, Long id) throws SQLException {
        int index = 1;
        preparedStatement.setString(index, name);
        index++;
        preparedStatement.setString(index, tagLine);
        index++;
        preparedStatement.setString(index, genre);
        index++;
        preparedStatement.setInt(index, ageRestriction);
        index++;
        preparedStatement.setInt(index, duration);
        index++;
        preparedStatement.setString(index, description);
        index++;
        preparedStatement.setDate(index, Date.valueOf(premiere));
        index++;
        preparedStatement.setString(index, cover);
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
                film.setGenre(resultSet.getString(RESULT_GENRE));
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
            rating.setVotes(resultSet.getInt(RESULT_VOTES));
            return rating;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void saveFilmFilmMember(Long idFilm, Long idFilmMember) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_FILM_FILM_MEMBER);
            int index = 1;
            preparedStatement.setLong(index, idFilm);
            index++;
            preparedStatement.setLong(index, idFilmMember);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findByIdFilmFilmMember(Long id) {
        int count = 0;
        try {
            int index=1;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FILM_FILM_MEMBER);
            preparedStatement.setLong(index,id);
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
    public List<Long> findByIdFilmMember(Long id) {
        try {
            List<Long> idMembers = new ArrayList<>();
            int index=1;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ID_FILM_MEMBER);
            preparedStatement.setLong(index,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                idMembers.add(resultSet.getLong(ID_FILM_MEMBER));
                found = resultSet.next();
            }
            return idMembers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteFilmFilmMember(Long idFilm, Long idMember) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILM_MEMBER);
            int index = 1;
            preparedStatement.setLong(index, idFilm);
            index++;
            preparedStatement.setLong(index, idMember);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeMemberList(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LIST_MEMBER);
            int index = 1;
            preparedStatement.setLong(index, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int findByIdFilmMemberFilm(Long id) {
        int count = 0;
        try {
            int index=1;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FILM_MEMBER_FILM);
            preparedStatement.setLong(index,id);
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
    public List<Long> findByIdFilm(Long id) {
        try {
            List<Long> idMembers = new ArrayList<>();
            int index=1;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ID_FILM);
            preparedStatement.setLong(index,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            while (found) {
                idMembers.add(resultSet.getLong(RESULT_ID_FILM));
                found = resultSet.next();
            }
            return idMembers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean findMember(Long idFilm ,Long idMember) {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_MEMBER);
            int index = 1;
            preparedStatement.setLong(index,idMember);
            index++;
            preparedStatement.setLong(index,idFilm);
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

    @Override
    public List<Long> findReviewsInFilm(Long idFilm) {
        try {
            List<Long> idReviews = new ArrayList<>();
            int index = 1;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ID_REVIEW);
            preparedStatement.setLong(index, idFilm);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            while (found) {
                idReviews.add(resultSet.getLong(ID_REVIEW));
                found = resultSet.next();
            }
            return idReviews;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeReview(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW);
            int index = 1;
            preparedStatement.setLong(index, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Film findByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
            int index = 1;
            preparedStatement.setString(index, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (!found) return null;
            Film film = new Film();
            film.setId(resultSet.getLong(RESULT_ID));
            film.setName(resultSet.getString(RESULT_NAME));
            film.setTagLine(resultSet.getString(RESULT_TAG_LINE));
            film.setGenre(resultSet.getString(RESULT_GENRE));
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
}
