package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.ReviewDao;
import com.epam.irasov.filmlibrary.entity.Review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedReviewOperationAction implements Action {
    private static final int PUBLISHED = 1;
    private static final int REMOVE = 2;
    private static final String MESSAGE_ERROR = "reviews.null";

    public SelectedReviewOperationAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == PUBLISHED) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                ReviewDao reviewDao = daoFactory.newReviewDao();
                if (reviewDao.emptyPublished()) {
                    req.getSession().setAttribute("selectedAction", "");
                    req.setAttribute("messageErrorReview", MESSAGE_ERROR);
                    return new View("operation-with-reviews", false);
                }
                List<Review> reviews = reviewDao.selectEmptyReviews();
                req.getSession().setAttribute("review","");
                req.getSession().setAttribute("reviews", reviews);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", PUBLISHED);
            return new View("operation-with-reviews", false);
        }

        if (Integer.parseInt(selected) == REMOVE) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                ReviewDao reviewDao = daoFactory.newReviewDao();
                if (reviewDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", "");
                    req.setAttribute("messageErrorReview", MESSAGE_ERROR);
                    return new View("operation-with-reviews", false);
                }
                List<Review> reviews = reviewDao.selectReviews();
                req.getSession().setAttribute("review","");
                req.getSession().setAttribute("reviews", reviews);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", REMOVE);
            return new View("operation-with-reviews", false);
        }
        return new View("operation-with-reviews", false);
    }
}
