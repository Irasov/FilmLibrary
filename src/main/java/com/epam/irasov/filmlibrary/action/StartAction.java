package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmBlockDao;
import com.epam.irasov.filmlibrary.dao.NewsBlockDao;
import com.epam.irasov.filmlibrary.entity.FilmBlock;
import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartAction implements Action {
    private final static String ID_NEWS_BLOCK = "1";
    private final static String ID_FILM_BLOCK = "2";

    public StartAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        NewsBlock newsBlock;
        FilmBlock filmBlock;
        HttpSession session = req.getSession();
        try {
            int cont = daoFactory.newSystemMemberDao().findType();
            if (cont == 0) {
                return new View("registration", true);
            }
            NewsBlockDao newsBlockDao = daoFactory.newNewsBlockDao();
            if (!newsBlockDao.emptyTable()) {
                newsBlock = newsBlockDao.findByIDNewsBlock(Long.parseLong(ID_NEWS_BLOCK));
                session.setAttribute("newsBlock", newsBlock);
            }
            FilmBlockDao filmBlockDao = daoFactory.newFilmBlockDao();
            if (!filmBlockDao.emptyTable()) {
                filmBlock = filmBlockDao.findByIDFilmBlock(Long.parseLong(ID_FILM_BLOCK));
                session.setAttribute("filmBlock", filmBlock);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("home", true);
    }
}
