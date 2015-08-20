package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmBlockDao;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmBlock;
import com.epam.irasov.filmlibrary.logic.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedActionFilmBlockAction implements Action {
    private static final Long ID_FILM_BLOCK = 2l;
    private static final int FILM_BLOCK_ADD = 1;
    private static final int REMOVE_FILM_FROM_FILM_BLOCK = 2;
    private static final String MESSAGE_BLOCK = "film.null";
    private static final String MESSAGE_FILM = "films.block.null";
    public SelectedActionFilmBlockAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == FILM_BLOCK_ADD) {
            req.getSession().setAttribute("selectedAction", FILM_BLOCK_ADD);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                FilmDao filmDao = daoFactory.newFilmDao();
                if (filmDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", FILM_BLOCK_ADD);
                    req.setAttribute("messageErrorBlockFilms", MESSAGE_FILM);
                    req.getSession().setAttribute("filmsInBlock", "");
                    daoFactory.endTx();
                    return new View("operation-with-films-block", false);
                }
                List<Film> films = filmDao.selectFilms();
                Operation.sortFilm(films, Operation.SORT_NAME);
                req.getSession().setAttribute("filmsInBlock", films);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-films-block", false);
        }
        if (Integer.parseInt(selected) == REMOVE_FILM_FROM_FILM_BLOCK) {
            req.getSession().setAttribute("selectedAction", REMOVE_FILM_FROM_FILM_BLOCK);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                FilmBlockDao filmBlockDao = daoFactory.newFilmBlockDao();
                if (filmBlockDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", REMOVE_FILM_FROM_FILM_BLOCK);
                    req.setAttribute("messageErrorBlockFilms", MESSAGE_BLOCK);
                    req.getSession().setAttribute("filmsBlock", "");
                    daoFactory.endTx();
                    return new View("operation-with-films-block", false);
                }
                daoFactory.beginTx();
                FilmBlock filmBlock = filmBlockDao.findByIDFilmBlock(ID_FILM_BLOCK);
                req.getSession().setAttribute("filmsBlock", filmBlock);
                Operation.sortFilm(filmBlock.getFilms(), Operation.SORT_NAME);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-films-block", false);
        }
        return new View("operation-with-films-block", false);
    }
}
