package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.NewsDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class RemoveNews implements Action {
    private final static int ADD_NEWS = 1;

    public RemoveNews() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectNews = Long.parseLong(req.getParameter("idNews").substring(0, req.getParameter("idNews").indexOf("+")));
        String image = req.getParameter("idNews").substring(req.getParameter("idNews").indexOf("+") + 1, req.getParameter("idNews").length());
        String applicationPath = req.getServletContext().getRealPath("");
        File file = new File(applicationPath + image);
        boolean res = file.delete();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            NewsDao newsDao = daoFactory.newNewsDao();
            newsDao.remove(selectNews);
            req.getSession().setAttribute("selectedAction", ADD_NEWS);
            req.getSession().setAttribute("message", "remove.message");
            req.getSession().setAttribute("news", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-news", true);
    }
}
