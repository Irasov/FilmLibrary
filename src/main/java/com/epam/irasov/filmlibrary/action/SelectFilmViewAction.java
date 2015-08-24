package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.*;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.Review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SelectFilmViewAction implements Action {
    public SelectFilmViewAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("id"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmDao filmDao = daoFactory.newFilmDao();
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            Film film = filmDao.findById(selectFilm);
            if (filmDao.findReviewsInFilm(selectFilm) != null) {
                List<Long> idReviews = filmDao.findReviewsInFilm(selectFilm);
                for (Long id : idReviews) {
                    Review review = reviewDao.findById(id);
                    film.addReviews(review);
                }
            }
            req.getSession().setAttribute("filmView", film);
            if (!(filmDao.findByIdFilmFilmMember(selectFilm) == 0)) {
                List<Long> idMember = filmDao.findByIdFilmMember(selectFilm);
                List<FilmMember> members = new ArrayList<>();
                for (Long id : idMember) {
                    FilmMember filmMember = filmMemberDao.findById(id);
                    members.add(filmMember);
                }
                req.getSession().setAttribute("filmMembersViewIn", members);
            }else{
                req.getSession().setAttribute("filmMembersViewIn","");
            }
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("film", true);
    }
}
