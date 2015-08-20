package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.logic.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedActionFilmAction implements Action {
    private final static int ADD_FILM = 1;
    private final static int EDIT_FILMS = 2;
    private final static int FILM_ADD_MEMBER = 3;
    private final static int REMOVE_FILM_FILM_MEMBER = 4;
    private final static int REMOVE_FILM = 5;
    private static final String MESSAGE = "film.null";
    private static final String MESSAGE_MEMBER = "film.or.member.null";

    public SelectedActionFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == ADD_FILM) {
            req.getSession().setAttribute("selectedAction", ADD_FILM);
            return new View("operation-with-movies", false);
        }
        if (Integer.parseInt(selected) == EDIT_FILMS) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                FilmDao filmDao = daoFactory.newFilmDao();
                if (filmDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", EDIT_FILMS);
                    req.setAttribute("messageError", MESSAGE);
                    return new View("operation-with-movies", false);
                }
                List<Film> films = filmDao.selectFilms();
                Operation.sortFilm(films, Operation.SORT_NAME);
                req.getSession().setAttribute("films", films);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", EDIT_FILMS);
            return new View("operation-with-movies", false);
        }
        if (Integer.parseInt(selected) == FILM_ADD_MEMBER) {
            req.getSession().setAttribute("selectedAction", FILM_ADD_MEMBER);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                FilmDao filmDao = daoFactory.newFilmDao();
                FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
                if (filmDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", FILM_ADD_MEMBER);
                    req.setAttribute("messageError", MESSAGE_MEMBER);
                    daoFactory.endTx();
                    return new View("operation-with-movies", false);
                }
                if (filmMemberDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", FILM_ADD_MEMBER);
                    req.setAttribute("messageError", MESSAGE_MEMBER);
                    daoFactory.endTx();
                    return new View("operation-with-movies", false);
                }
                List<Film> films = filmDao.selectFilms();
                Operation.sortFilm(films, Operation.SORT_NAME);
                req.getSession().setAttribute("films", films);
                List<FilmMember> filmMembers = filmMemberDao.selectFilmMember();
                Operation.sortFilmMember(filmMembers, Operation.SORT_NAME);
                req.getSession().setAttribute("filmMembers", filmMembers);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-movies", false);
        }
        if (Integer.parseInt(selected) == REMOVE_FILM) {
            req.getSession().setAttribute("selectedAction", REMOVE_FILM);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                FilmDao filmDao = daoFactory.newFilmDao();
                if (filmDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", REMOVE_FILM);
                    req.setAttribute("messageError", MESSAGE);
                    return new View("operation-with-movies", false);
                }
                List<Film> films = filmDao.selectFilms();
                Operation.sortFilm(films, Operation.SORT_NAME);
                req.getSession().setAttribute("films", films);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-movies", false);
        }
        if (Integer.parseInt(selected) == REMOVE_FILM_FILM_MEMBER) {
            req.getSession().setAttribute("selectedAction", REMOVE_FILM_FILM_MEMBER);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                FilmDao filmDao = daoFactory.newFilmDao();
                if (filmDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", REMOVE_FILM_FILM_MEMBER);
                    req.setAttribute("messageError", MESSAGE);
                    return new View("operation-with-movies", false);
                }
                daoFactory.beginTx();
                List<Film> films = filmDao.selectFilms();
                Operation.sortFilm(films, Operation.SORT_NAME);
                req.getSession().setAttribute("films", films);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-movies", false);
        }
        return new View("operation-with-movies", false);
    }
}
