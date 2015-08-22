package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.RatingDao;
import com.epam.irasov.filmlibrary.dao.ReviewDao;
import com.epam.irasov.filmlibrary.entity.Rating;
import com.epam.irasov.filmlibrary.entity.Review;
import com.epam.irasov.filmlibrary.entity.SystemMember;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AddReviewsAction implements Action {
    private static final Long ID_REVIEW = 1l;
    private static final boolean PUBLISHED = false;
    private final static Long ID_RATING = 1l;
    private final static int VOTES_RATING = 0;


    public AddReviewsAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String reviewText = req.getParameter("reviewText");
        String status = req.getParameter("status");
        Long idFilm = Long.parseLong(req.getParameter("idFilm"));
        String textError = Validator.isTextValid(reviewText);
        Long idRating;
        Long idReview;
        if (textError != null) {
            req.setAttribute("textError", textError);
            return new View("film", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            SystemMember systemMember = (SystemMember) req.getSession().getAttribute("systemMember");
            daoFactory.beginTx();
            RatingDao ratingDao = daoFactory.newRatingDao();
            Rating rating;
            if (ratingDao.findId() != null) {
                idRating = ratingDao.findId();
                idRating++;
                rating = ratingDao.save(new Rating(idRating, systemMember.getName(), VOTES_RATING));
            } else {
                rating = ratingDao.save(new Rating(ID_RATING, systemMember.getName(), VOTES_RATING));
            }
            Review review;
            if (reviewDao.findId() != null) {
                idReview = reviewDao.findId();
                idReview++;
                review = new Review(idReview, systemMember.getLogin(), LocalDate.now(), reviewText, status, rating, PUBLISHED);
            } else {
                review = new Review(ID_REVIEW, systemMember.getLogin(), LocalDate.now(), reviewText, status, rating, PUBLISHED);
            }
            reviewDao.save(review);
            reviewDao.saveFilmReview(idFilm, review.getId());
            reviewDao.saveSystemMemberReview(systemMember.getId(), review.getId());
            req.setAttribute("messageReview", "review.add.complete");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("film", false);
    }
}
