package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveMyReview implements Action {
    public RemoveMyReview() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectReview = Long.parseLong(req.getParameter("id"));
        Long idRating = Long.parseLong(req.getParameter("idRating"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            FilmDao filmDao = daoFactory.newFilmDao();
            RatingDao ratingDao = daoFactory.newRatingDao();
            SystemMemberDao systemMemberDao = daoFactory.newSystemMemberDao();
            systemMemberDao.removeReview(selectReview);
            filmDao.removeReview(selectReview);
            reviewDao.remove(selectReview);
            ratingDao.remove(idRating);
            req.getSession().setAttribute("selectedAction", "");
            req.setAttribute("messageReview", "remove.message");
            req.getSession().setAttribute("my-reviews", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("my-reviews", false);
    }
}
