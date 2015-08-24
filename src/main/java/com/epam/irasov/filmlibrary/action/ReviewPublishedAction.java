package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.ReviewDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewPublishedAction implements Action{
    private static final boolean PUBLISHED = true;
    public ReviewPublishedAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            reviewDao.upDate(id,PUBLISHED);
            req.setAttribute("messageReview", "review.published.true");
            req.getSession().setAttribute("selectedAction", "");
            req.getSession().setAttribute("review", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-reviews", false);
    }
}
