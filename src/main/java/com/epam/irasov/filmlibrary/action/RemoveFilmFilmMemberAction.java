package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveFilmFilmMemberAction implements Action {

    public RemoveFilmFilmMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        Long selectFilm = Long.parseLong(req.getParameter("idFilm"));
        try {
            daoFactory.beginTx();
            FilmDao filmDao = daoFactory.newFilmDao();
            for (String idMember : req.getParameterValues("idMember")) {
                filmDao.deleteFilmFilmMember(selectFilm, Long.parseLong(idMember));
            }
            req.getSession().setAttribute("selectedAction", "");
            req.getSession().setAttribute("message", "remove.message");
            req.getSession().setAttribute("filmMembersRemove", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-movies", true);
    }
}
