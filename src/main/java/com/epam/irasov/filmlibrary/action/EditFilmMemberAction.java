package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class EditFilmMemberAction implements Action {
    public EditFilmMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String surName = req.getParameter("surname");
        String birthDate = req.getParameter("birthdate");
        String photo = req.getParameter("photo");
        System.out.println("PHOTO"+photo);
        Long idType = Long.parseLong(req.getParameter("idType"));
        String nameType = req.getParameter("nameType");
        DaoFactory daoFactory = DaoFactory.getInstance();
        try{
        daoFactory.beginTx();
        FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
        filmMemberDao.upDate(new FilmMember(id, name, patronymic, surName, LocalDate.parse(birthDate, ofPattern("yyyy-MM-dd")), photo, new FilmMember.Type(idType,nameType)));
        req.getSession().setAttribute("message", "edit.message");
        req.getSession().setAttribute("selectedAction", "");
        req.getSession().setAttribute("filmMember", "");
        daoFactory.endTx();
    } catch (Exception e) {
        throw new DaoException(e);
    } finally {
        daoFactory.close();
    }
    return new View("operation-with-members-film", true);
    }
}
