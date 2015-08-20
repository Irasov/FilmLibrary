package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.NewsBlockDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveNewsBlockAction implements Action {
    public RemoveNewsBlockAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            NewsBlockDao newsBlockDao = daoFactory.newNewsBlockDao();
            for (String idNews : req.getParameterValues("idNews")) {
                newsBlockDao.deleteNews(Long.parseLong(idNews));
            }
            req.getSession().setAttribute("selectedAction", "");
            req.setAttribute("messageBlock", "remove.message");
            req.getSession().setAttribute("newsBlock","");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-news-block", false);
    }
}
