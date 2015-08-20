package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.NewsBlockDao;

import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

public class BlockAddNewsAction implements Action {
    private static final String MESSAGE = "block.add.news";

    public BlockAddNewsAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("idNews"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            NewsBlockDao newsBlockDao = daoFactory.newNewsBlockDao();
            if(newsBlockDao.findNews(id)){
                req.setAttribute("messageRepeat","repeat.news");
                daoFactory.endTx();
                return new View("operation-with-news-block", false);
            }
            newsBlockDao.addNews(id);
            req.setAttribute("messageBlock", MESSAGE);
            req.getSession().setAttribute("selectedAction", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-news-block", false);
    }
}