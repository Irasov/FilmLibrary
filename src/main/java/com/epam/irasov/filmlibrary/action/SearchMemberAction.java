package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchMemberAction implements Action {
    public SearchMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String surName = req.getParameter("surName").toLowerCase();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            FilmMember filmMember =  filmMemberDao.findBySurname(surName);
            if(filmMember==null){
                req.setAttribute("messageSearchMember", "member.not.found");
                return new View("search", false);
            }
            req.setAttribute("memberSearch", filmMember);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("search", false);
    }
}
