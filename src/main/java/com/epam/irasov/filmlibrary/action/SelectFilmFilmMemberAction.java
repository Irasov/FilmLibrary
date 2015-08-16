package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmDao;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SelectFilmFilmMemberAction implements Action{
    private static final String MESSAGE = "film.member.null.film";

    public SelectFilmFilmMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilm = Long.parseLong(req.getParameter("idFilm"));
        DaoFactory daoFactory = DaoFactory.getInstance();
        try{
            daoFactory.beginTx();
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            FilmDao filmDao = daoFactory.newFilmDao();
            if(filmDao.findByIdFilmFilmMember(selectFilm)==0){
                req.getSession().setAttribute("filmMembersRemove","");
                req.setAttribute("errorMembers",MESSAGE);
                daoFactory.endTx();
                return new View("operation-with-movies", false);
            }
            List<Long> idMember = filmDao.findByIdFilmMember(selectFilm);
            List<FilmMember> members = new ArrayList<>();
            for(Long id:idMember) {
                FilmMember filmMember = filmMemberDao.findById(id);
                members.add(filmMember);
            }
            req.getSession().setAttribute("filmMembersRemove",members);
            req.getSession().setAttribute("idFilmRemove",selectFilm);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-movies", false);
    }
}
