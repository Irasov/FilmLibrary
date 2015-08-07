package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.SystemMemberDao;
import com.epam.irasov.filmlibrary.entity.*;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class RegistrationAction implements Action {
    private final static Long ID_ADMIN = 1l;
    private final static Long ID_USER = 2l;
    private final static String NAME_ADMIN = "admin";
    private final static String NAME_USER = "user";
    private final static int NO_ADMINISTRATOR = 0;
    private final static int NO_USER = 1;
    private final static long NEWS_BLOCK_ID_VALUE = 1l;
    private final static long FILM_BLOCK_ID_VALUE = 2l;
    private final static String NEWS_BLOCK_NAME_INITIAL_VALUE = "News from the world of cinema";
    private final static String FILM_BLOCK_NAME_INITIAL_VALUE = "New Movies on the site";

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
        String loginError = Validator.isLoginValid(login);
        String passwordError = Validator.isPasswordValid(password);
        String emailError = Validator.isEmailValid(email);
        if (loginError != null) {
            req.setAttribute("loginError", loginError);
            return new View("registration", false);
        } else if (passwordError != null) {
            req.setAttribute("passwordError", passwordError);
            return new View("registration", false);
        } else if (emailError != null){
            req.setAttribute("emailError", emailError);
            return new View("registration", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        Member.Type type = null;
        try {
            int cont = daoFactory.newSystemMemberDao().findType();
            if (cont == NO_ADMINISTRATOR) {
                type = daoFactory.newSystemMemberDao().saveType(new Member.Type(ID_ADMIN, NAME_ADMIN));
                daoFactory.newNewsBlockDao().save(new NewsBlock(NEWS_BLOCK_ID_VALUE, NEWS_BLOCK_NAME_INITIAL_VALUE));
                daoFactory.newFilmBlockDao().save(new FilmBlock(FILM_BLOCK_ID_VALUE, FILM_BLOCK_NAME_INITIAL_VALUE));
            } else if (cont == NO_USER) {
                type = daoFactory.newSystemMemberDao().saveType(new Member.Type(ID_USER, NAME_USER));
            } else if ((cont > NO_USER)) {
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
        return new View("index", true);
    }
}
