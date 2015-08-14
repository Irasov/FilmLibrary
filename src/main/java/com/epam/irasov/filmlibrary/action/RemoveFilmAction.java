package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.dao.RatingDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class RemoveFilmAction implements Action {
    private final static int ADD_FILM = 1;

    public RemoveFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("idFilm"));
        String cover = req.getParameter("cover");
        String applicationPath = req.getServletContext().getRealPath("");
        System.out.println(cover);
        File file = new File(applicationPath + cover);
        boolean res = file.delete();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            FilmDao filmDao = daoFactory.newFilmDao();
            Long idRating = filmDao.remove(selectFilm);
            RatingDao ratingDao = daoFactory.newRatingDao();
            ratingDao.remove(idRating);
            req.getSession().setAttribute("selectedAction", ADD_FILM);
            req.getSession().setAttribute("message", "remove.message");
            req.getSession().setAttribute("films", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-movies", true);
    }
}