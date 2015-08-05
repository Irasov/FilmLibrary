package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.SystemMemberDao;
import com.epam.irasov.filmlibrary.entity.Member;
import com.epam.irasov.filmlibrary.entity.SystemMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class RegistrationAction implements Action {
    private final static Long ID_ADMIN = 1l;
    private final static Long ID_USER = 2l;
    private final static String NAME_ADMIN = "admin";
    private final static String NAME_USER = "user";

    public RegistrationAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("pas");
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String surName = req.getParameter("surname");
        String birthDate = req.getParameter("birthdate");
        String email = req.getParameter("email");
        DaoFactory daoFactory = DaoFactory.getInstance();
        Member.Type type = null;
        try {
            int cont = daoFactory.newSystemMemberDao().findType();
            if (cont == 0) {
                type = daoFactory.newSystemMemberDao().saveType(new Member.Type(ID_ADMIN, NAME_ADMIN));
            } else if (cont == 1) {
                type = daoFactory.newSystemMemberDao().saveType(new Member.Type(ID_USER, NAME_USER));
            } else if ((cont > 1)) {
                type = (new Member.Type(ID_USER, NAME_USER));
            }
            SystemMember systemMember = new SystemMember();
            systemMember.setLogin(login);
            systemMember.setPassword(password);
            systemMember.setName(name);
            systemMember.setPatronymic(patronymic);
            systemMember.setSurname(surName);
            systemMember.setType(type);
            systemMember.setBirthDate(LocalDate.parse(birthDate, ofPattern("yyyy-MM-dd")));
            systemMember.setEmail(email);
            daoFactory.beginTx();
            SystemMemberDao systemMemberDao = daoFactory.newSystemMemberDao();
            systemMemberDao.save(systemMember);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("home", true);
    }
}
