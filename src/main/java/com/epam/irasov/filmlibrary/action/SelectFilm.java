package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.logic.Sorting;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectFilm implements Action {
    public SelectFilm() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("idFilm"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            FilmDao filmDao = daoFactory.newFilmDao();
            Film film = filmDao.findById(selectFilm);
            System.out.println(film.getRating().getId());
            req.getSession().setAttribute("film", film);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-movies", false);
    }
}
