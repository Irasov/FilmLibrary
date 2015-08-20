package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmBlockDao;
import com.epam.irasov.filmlibrary.entity.FilmBlock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockAddFilmAction implements Action {
    private static final String MESSAGE = "block.add.films";
    public BlockAddFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("idFilm"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmBlockDao filmBlockDao = daoFactory.newFilmBlockDao();
            filmBlockDao.addFilm(id);
            req.setAttribute("messageBlockFilms", MESSAGE);
            req.getSession().setAttribute("selectedAction", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-films-block", false);
    }
}
