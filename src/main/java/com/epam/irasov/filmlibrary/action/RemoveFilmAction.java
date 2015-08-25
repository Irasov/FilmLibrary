package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.*;
import com.epam.irasov.filmlibrary.entity.Review;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class RemoveFilmAction implements Action {

    public RemoveFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("idFilm").substring(0, req.getParameter("idFilm").indexOf("+")));
        String cover = req.getParameter("idFilm").substring(req.getParameter("idFilm").indexOf("+") + 1, req.getParameter("idFilm").length());
        String applicationPath = req.getServletContext().getRealPath("");
        File file = new File(applicationPath + cover);
        boolean res = file.delete();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmDao filmDao = daoFactory.newFilmDao();
            ReviewDao reviewDao = daoFactory.newReviewDao();
            FilmBlockDao filmBlockDao = daoFactory.newFilmBlockDao();
            SystemMemberDao systemMemberDao = daoFactory.newSystemMemberDao();
            RatingDao ratingDao = daoFactory.newRatingDao();
            if (filmDao.findReviewsInFilm(selectFilm) != null) {
                List<Long> idReviews = filmDao.findReviewsInFilm(selectFilm);
                for (Long id : idReviews) {
                    filmDao.removeReview(id);
                    systemMemberDao.removeReview(id);
                    filmDao.removeReview(id);
                    Long idRating = reviewDao.findIdRating(id);
                    reviewDao.remove(id);
                    ratingDao.remove(idRating);
                }
            }
            filmBlockDao.deleteFilm(selectFilm);
            filmDao.removeMemberList(selectFilm);
            Long idRating = filmDao.remove(selectFilm);
            ratingDao = daoFactory.newRatingDao();
            ratingDao.remove(idRating);
            req.getSession().setAttribute("selectedAction", "");
            req.setAttribute("message", "remove.message");
            req.getSession().setAttribute("films", "");
            req.getSession().setAttribute("filmsView","");
            req.getSession().setAttribute("filmsInBlock","");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-movies", false);
    }
}
