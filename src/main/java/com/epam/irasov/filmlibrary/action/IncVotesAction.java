package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.*;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.Review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class IncVotesAction implements Action {
    public IncVotesAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("id"));
        Long selectRating = Long.parseLong(req.getParameter("idRating"));
        int votes = Integer.parseInt(req.getParameter("votes"));
        votes++;
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            RatingDao ratingDao = daoFactory.newRatingDao();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            FilmDao filmDao = daoFactory.newFilmDao();
            ratingDao.upDate(selectRating, votes);
            Film film = filmDao.findById(selectFilm);
            if (filmDao.findReviewsInFilm(selectFilm) != null) {
                List<Long> idReviews = filmDao.findReviewsInFilm(selectFilm);
                for (Long id : idReviews) {
                    Review review = reviewDao.findById(id);
                    film.addReviews(review);
                }
            }
            req.getSession().setAttribute("filmView", film);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("film", false);
    }
}
