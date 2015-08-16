package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.Film;
import com.epam.irasov.filmlibrary.entity.FilmMember;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class AddMemberAction implements Action {
    private final static Long ID_MEMBER= 1l;
    private final static String MESSAGE = "add.film.member";
    private static final String PATH_PHOTO = "img/site/";

    public AddMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String surName = req.getParameter("surname");
        String birthDate = req.getParameter("birthdate");
        String photo = req.getParameter("fileName");
        String idType = req.getParameter("type").substring(0, req.getParameter("type").indexOf("+"));
        String nameType = req.getParameter("type").substring(req.getParameter("type").indexOf("+")+1,req.getParameter("type").length());
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmMember.Type type = new FilmMember.Type(Long.parseLong(idType),nameType);
            FilmMember filmMember = new FilmMember(ID_MEMBER,name,patronymic,surName, LocalDate.parse(birthDate, ofPattern("yyyy-MM-dd")),PATH_PHOTO+photo,type);
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            filmMemberDao.saveFilmMember(filmMember);
            req.getSession().setAttribute("messageFilmMember", MESSAGE);
            req.getSession().setAttribute("selectedAction", "");
            req.getSession().setAttribute("filmMember","");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-members-film", true);
    }


}
