package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.NewsBlockDao;
import com.epam.irasov.filmlibrary.dao.NewsDao;
import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;
import com.epam.irasov.filmlibrary.logic.Operation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedActionNewsBlockAction implements Action {
    private static final Long ID_NEWS_BLOCK = 1l;
    private static final int NEWS_BLOCK_ADD = 1;
    private static final int REMOVE_NEWS_FROM_NEWS_BLOCK = 2;
    private static final String MESSAGE_BLOCK = "news.null";
    private static final String MESSAGE_NEWS = "news.block.null";

    public SelectedActionNewsBlockAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == NEWS_BLOCK_ADD) {
            req.getSession().setAttribute("selectedAction", NEWS_BLOCK_ADD);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
                NewsDao newsDao = daoFactory.newNewsDao();
                if (newsDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", NEWS_BLOCK_ADD);
                    req.setAttribute("messageErrorBlock", MESSAGE_NEWS);
                    req.getSession().setAttribute("newsInBlock", "");
                    daoFactory.endTx();
                    return new View("operation-with-news-block", false);
                }
                List<News> news = newsDao.selectNews();
                Operation.sortNews(news, Operation.SORT_DATE);
                req.getSession().setAttribute("newsInBlock", news);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-news-block", false);
        }
        if (Integer.parseInt(selected) == REMOVE_NEWS_FROM_NEWS_BLOCK) {
            req.getSession().setAttribute("selectedAction", REMOVE_NEWS_FROM_NEWS_BLOCK);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                NewsBlockDao newsBlockDao = daoFactory.newNewsBlockDao();
                if (newsBlockDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", REMOVE_NEWS_FROM_NEWS_BLOCK);
                    req.setAttribute("messageErrorBlock", MESSAGE_BLOCK);
                    req.getSession().setAttribute("newsBlock", "");
                    daoFactory.endTx();
                    return new View("operation-with-news-block", false);
                }
                daoFactory.beginTx();
                NewsBlock newsBlock = newsBlockDao.findByIDNewsBlock(ID_NEWS_BLOCK);
                req.getSession().setAttribute("newsBlock", newsBlock);
                Operation.sortNews(newsBlock.getNews(), Operation.SORT_DATE);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-news-block", false);
        }
        return new View("operation-with-news-block", false);
    }
}
