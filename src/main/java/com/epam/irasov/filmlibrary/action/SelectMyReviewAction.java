package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.ReviewDao;
import com.epam.irasov.filmlibrary.entity.Review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectMyReviewAction implements Action {
    public SelectMyReviewAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectReview = Long.parseLong(req.getParameter("idReview"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            Review review = reviewDao.findById(selectReview);
            req.getSession().setAttribute("myReview", review);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("my-reviews", false);
    }
}
