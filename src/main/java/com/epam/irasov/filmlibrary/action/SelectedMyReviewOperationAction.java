package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.ReviewDao;
import com.epam.irasov.filmlibrary.entity.Review;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SelectedMyReviewOperationAction implements Action {
    private static final int EDIT = 1;
    private static final int REMOVE = 2;
    private static final String MESSAGE_ERROR = "my.reviews.null";

    public SelectedMyReviewOperationAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        SystemMember systemMember = (SystemMember) req.getSession().getAttribute("systemMember");
        if (Integer.parseInt(selected) == EDIT) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                ReviewDao reviewDao = daoFactory.newReviewDao();
                if (reviewDao.emptyMyReview(systemMember.getId()) == null) {
                    req.getSession().setAttribute("selectedAction", "");
                    req.setAttribute("messageErrorReview", MESSAGE_ERROR);
                    return new View("my-reviews", false);
                }
                List<Long> idReviews = reviewDao.emptyMyReview(systemMember.getId());
                List<Review> reviews = new ArrayList<>();
                for (Long id : idReviews) {
                    reviews.add(reviewDao.findById(id));
                }
                req.getSession().setAttribute("myReview", "");
                req.getSession().setAttribute("myReviews", reviews);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", EDIT);
            return new View("my-reviews", false);
        }

        if (Integer.parseInt(selected) == REMOVE) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                ReviewDao reviewDao = daoFactory.newReviewDao();
                if (reviewDao.emptyMyReview(systemMember.getId()) == null) {
                    req.getSession().setAttribute("selectedAction", "");
                    req.setAttribute("messageErrorReview", MESSAGE_ERROR);
                    return new View("my-reviews", false);
                }
                List<Long> idReviews = reviewDao.emptyMyReview(systemMember.getId());
                List<Review> reviews = new ArrayList<>();
                for (Long id : idReviews) {
                    reviews.add(reviewDao.findById(id));
                }
                req.getSession().setAttribute("myReview", "");
                req.getSession().setAttribute("myReviews", reviews);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", REMOVE);
            return new View("my-reviews", false);
        }
        return new View("my-reviews", false);
    }
}
