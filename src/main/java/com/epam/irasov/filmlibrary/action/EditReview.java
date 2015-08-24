package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.ReviewDao;
import com.epam.irasov.filmlibrary.entity.Review;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditReview implements Action {
    public EditReview() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String text = req.getParameter("reviewText");
        Long id = Long.parseLong(req.getParameter("id"));
        String textError = Validator.isTextValid(text);
        if (textError != null) {
            req.setAttribute("textError", textError);
            return new View("my-reviews", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            reviewDao.upDateText(id,text);
            req.setAttribute("messageReview", "review.complete.edit");
            req.getSession().setAttribute("selectedAction", "");
            req.getSession().setAttribute("review", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("my-reviews", false);
    }
}
