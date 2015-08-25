package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.*;
import com.epam.irasov.filmlibrary.entity.FilmBlock;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.entity.NewsBlock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartAction implements Action {
    private final static String ID_NEWS_BLOCK = "1";
    private final static String ID_FILM_BLOCK = "2";
    private final static Long ID_ACTOR = 1l;
    private final static String NAME_ACTOR = "actor";
    private final static Long ID_PRODUCER = 2l;
    private final static String NAME_PRODUCER = "producer";
    private final static Long ID_OPERATOR = 3l;
    private final static String NAME_OPERATOR = "operator";
    private final static Long ID_COMPOSER = 4l;
    private final static String NAME_COMPOSER = "composer";

    public StartAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        NewsBlock newsBlock;
        FilmBlock filmBlock;
        HttpSession session = req.getSession();
        FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
        int cont = filmMemberDao.findType();
        if (cont == 0) {
            FilmMember.Type type = new FilmMember.Type(ID_ACTOR, NAME_ACTOR);
            filmMemberDao.saveType(type);
            type = new FilmMember.Type(ID_PRODUCER, NAME_PRODUCER);
            filmMemberDao.saveType(type);
            type = new FilmMember.Type(ID_OPERATOR, NAME_OPERATOR);
            filmMemberDao.saveType(type);
            type = new FilmMember.Type(ID_COMPOSER, NAME_COMPOSER);
            filmMemberDao.saveType(type);
        }
        try {
            cont = daoFactory.newSystemMemberDao().findType();
            if (cont == 0) {
                return new View("registration", true);
            }
            NewsBlockDao newsBlockDao = daoFactory.newNewsBlockDao();
            if (!newsBlockDao.emptyTable()) {
                newsBlock = newsBlockDao.findByIDNewsBlock(Long.parseLong(ID_NEWS_BLOCK));
                session.setAttribute("newsBlock", newsBlock);
            }else {
                session.setAttribute("newsBlock", "");
            }
            FilmBlockDao filmBlockDao = daoFactory.newFilmBlockDao();
            if (!filmBlockDao.emptyTable()) {
                filmBlock = filmBlockDao.findByIDFilmBlock(Long.parseLong(ID_FILM_BLOCK));
                session.setAttribute("filmBlock", filmBlock);
            } else{
                session.setAttribute("filmBlock", "");
            }
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("home", true);
    }
}
