package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class EditFilmAction implements Action {
    private static final int FILM_ADD_MEMBER = 1;

    public EditFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String tagLine = req.getParameter("tagLine");
        String ageRestriction = req.getParameter("age");
        String duration = req.getParameter("duration");
        String description = req.getParameter("description");
        String premiere = req.getParameter("premiere");
        Long rating = Long.parseLong(req.getParameter("rating"));
        String cover = req.getParameter("cover");
        String genre = req.getParameter("genre");
        System.out.println(cover);
        String tagLineError = Validator.isTagLineValid(tagLine);
        String descriptionError = Validator.isDescriptionValid(description);
        String restrictionError = Validator.isRestrictionAgeValid(ageRestriction);
        if (tagLineError != null) {
            req.setAttribute("tagLineError", tagLineError);
            return new View("operation-with-movies", false);
        }
        if (descriptionError != null) {
            req.setAttribute("descriptionError", descriptionError);
            return new View("operation-with-movies", false);
        }
        if (restrictionError != null) {
            req.setAttribute("restrictionError", restrictionError);
            return new View("operation-with-movies", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmDao filmDao = daoFactory.newFilmDao();
            filmDao.upDate(new Film(id, name, tagLine, genre, LocalDate.parse(premiere, ofPattern("yyyy-MM-dd")), Integer.parseInt(ageRestriction), Integer.parseInt(duration), cover, daoFactory.newRatingDao().findbyId(rating), description));
            req.getSession().setAttribute("message", "edit.message");
            req.getSession().setAttribute("selectedAction", "");
            req.getSession().setAttribute("film", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-movies", true);
    }
}

