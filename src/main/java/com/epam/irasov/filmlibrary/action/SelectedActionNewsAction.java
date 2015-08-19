package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.NewsDao;
import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.logic.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedActionNewsAction implements Action {
    private static final int ADD_NEWS = 1;
    private static final int EDIT_NEWS = 2;
    private static final int REMOVE_NEWS = 3;
    private static final String MESSAGE_ERROR = "news.null";

    public SelectedActionNewsAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == ADD_NEWS) {
            req.getSession().setAttribute("selectedAction", ADD_NEWS);
            return new View("operation-with-news", false);
        }
        if (Integer.parseInt(selected) == EDIT_NEWS) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                NewsDao newsDao = daoFactory.newNewsDao();
                if (newsDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", EDIT_NEWS);
                    req.getSession().setAttribute("messageErrorNews", MESSAGE_ERROR);
                    return new View("operation-with-news", false);
                }
                List<News> news = newsDao.selectNews();
                Operation.sortNews(news, Operation.SORT_DATE);
                req.getSession().setAttribute("news", news);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", EDIT_NEWS);
            return new View("operation-with-news", false);
        }
        if (Integer.parseInt(selected) == REMOVE_NEWS) {
            req.getSession().setAttribute("selectedAction", REMOVE_NEWS);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                NewsDao newsDao = daoFactory.newNewsDao();
                if (newsDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", REMOVE_NEWS);
                    req.getSession().setAttribute("messageErrorNews", MESSAGE_ERROR);
                    daoFactory.endTx();
                    return new View("operation-with-news", false);
                }
                List<News> news = newsDao.selectNews();
                Operation.sortNews(news, Operation.SORT_DATE);
                req.getSession().setAttribute("news", news);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-news", false);
        }
        return new View("operation-with-news", false);
    }
}
