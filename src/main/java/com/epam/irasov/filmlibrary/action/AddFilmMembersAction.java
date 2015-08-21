package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFilmMembersAction implements Action {
    private static final Object MESSAGE = "film.add.film.member";

    public AddFilmMembersAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("idFilm"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmDao filmDao = daoFactory.newFilmDao();
            for(String idMember:req.getParameterValues("idMember")){
                if (!(filmDao.findMember(selectFilm, Long.parseLong(idMember)))) {
                    filmDao.saveFilmFilmMember(selectFilm, Long.parseLong(idMember));
                }
            }
            req.setAttribute("message", MESSAGE);
            req.getSession().setAttribute("selectedAction","");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-movies", false);
    }
}
