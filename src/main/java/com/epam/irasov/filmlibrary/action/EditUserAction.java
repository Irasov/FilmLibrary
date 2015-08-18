package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.SystemMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.entity.SystemMember;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class EditUserAction implements Action {
    public EditUserAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String surName = req.getParameter("surname");
        String birthDate = req.getParameter("birthdate");
        String photo = req.getParameter("photo");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Long idType = Long.parseLong(req.getParameter("idType"));
        String nameType = req.getParameter("nameType");
        String emailError = Validator.isEmailValid(email);
        if (emailError != null) {
            req.setAttribute("emailError", emailError);
            return new View("edit-personal-data", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            SystemMemberDao systemMemberDao = daoFactory.newSystemMemberDao();
            req.getSession().setAttribute("systemMember", systemMemberDao.upDate(new SystemMember(id, name, patronymic, surName, LocalDate.parse(birthDate, ofPattern("yyyy-MM-dd")), photo, login, password, email, new FilmMember.Type(idType, nameType))));
            req.getSession().setAttribute("messageEdit", "edit.user.message");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("edit-personal-data", true);
    }
}
