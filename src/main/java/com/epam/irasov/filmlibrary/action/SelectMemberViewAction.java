package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SelectMemberViewAction implements Action {
    public SelectMemberViewAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("idMemberView"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            FilmDao filmDao = daoFactory.newFilmDao();
            FilmMember filmMember = filmMemberDao.findById(selectFilm);
            if (!(filmDao.findByIdFilmMemberFilm(selectFilm) == 0)) {
                List<Long> idFilm = filmDao.findByIdFilm(selectFilm);
                List<Film> films = new ArrayList<>();
                for (Long id : idFilm) {
                    Film film = filmDao.findById(id);
                    films.add(film);
                }
                req.getSession().setAttribute("filmsView", films);
            }
            req.getSession().setAttribute("filmMemberView", filmMember);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("film-member", true);
    }
}
