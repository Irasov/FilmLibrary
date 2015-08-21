package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.Rating;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class AddFilmAction implements Action {
    private final static Long ID_FILM = 1l;
    private final static Long ID_RATING = 1l;
    private final static int VOTES_RATING = 0;
    private final static String PATH_IMAGE = "img/site/";
    private final static String MESSAGE = "film.add.complete";

    public AddFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String tagLine = req.getParameter("tagLine");
        String ageRestriction = req.getParameter("age");
        String duration = req.getParameter("duration");
        String description = req.getParameter("description");
        String premiere = req.getParameter("premiere");
        String cover = req.getParameter("fileName");
        String genre = req.getParameter("genre");

        String tagLineError = Validator.isTagLineValid(tagLine);
        String descriptionError = Validator.isDescriptionValid(description);
        String restrictionError = Validator.isRestrictionAgeValid(ageRestriction);
        if (tagLineError != null) {
            req.setAttribute("fileName", cover);
            req.setAttribute("tagLineError", tagLineError);
            return new View("operation-with-movies", false);
        }
        if (descriptionError != null) {
            req.setAttribute("descriptionError", descriptionError);
            req.setAttribute("fileName", cover);
            return new View("operation-with-movies", false);
        }
        if (restrictionError != null) {
            req.setAttribute("fileName", cover);
            req.setAttribute("restrictionError", restrictionError);
            return new View("operation-with-movies", false);
        }

        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            Rating rating = daoFactory.newRatingDao().save(new Rating(ID_RATING, name, VOTES_RATING));
            FilmDao filmDao = daoFactory.newFilmDao();
            filmDao.save(new Film(ID_FILM, name, tagLine, genre ,LocalDate.parse(premiere, ofPattern("yyyy-MM-dd")), Integer.parseInt(ageRestriction), Integer.parseInt(duration), PATH_IMAGE + cover, daoFactory.newRatingDao().findByName(name), description));
            req.setAttribute("message",MESSAGE);
            req.getSession().setAttribute("selectedAction","");
            req.getSession().setAttribute("film","");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }

        return new View("operation-with-movies", false);
    }
}
