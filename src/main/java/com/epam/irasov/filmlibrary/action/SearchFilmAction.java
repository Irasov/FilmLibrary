package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.entity.Film;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchFilmAction implements Action{
    public SearchFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String filmName = req.getParameter("name").toLowerCase();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmDao  filmDao = daoFactory.newFilmDao();
            Film film =  filmDao.findByName(filmName);
            if(film==null){
                req.setAttribute("messageSearchFilm", "film.not.found");
                return new View("search", false);
            }
            req.setAttribute("filmSearch", film);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("search", false);
    }
}
