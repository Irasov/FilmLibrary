package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.NewsBlockDao;
import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartAction implements Action{
    public StartAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        NewsBlock newsBlock = null;
        try {
            int cont = daoFactory.newSystemMemberDao().findType();
            if (cont == 0) {
                return new View("registration",true);
            }
            NewsBlockDao newsBlockDao = daoFactory.newNewsBlockDao();
            newsBlock  = newsBlockDao.findByIDNewsBlock(Long.parseLong("1"));
            HttpSession session = req.getSession();
            session.setAttribute("newsBlock", newsBlock);
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("home", true);
    }
}
