package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmBlockDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockRemoveFilmAction implements Action {
    public BlockRemoveFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                FilmBlockDao filmBlockDao = daoFactory.newFilmBlockDao();
                for (String idFilm : req.getParameterValues("idFilm")) {
                    filmBlockDao.deleteFilm(Long.parseLong(idFilm));
                }
                req.getSession().setAttribute("selectedAction", "");
                req.setAttribute("messageBlockFilms", "remove.message");
                req.getSession().setAttribute("filmsBlock","");
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-films-block", false);
        }
}
