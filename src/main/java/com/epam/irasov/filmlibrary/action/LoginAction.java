package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.SystemMemberDao;
import com.epam.irasov.filmlibrary.entity.SystemMember;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class LoginAction implements Action {

    public LoginAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login").toLowerCase();
        String password = req.getParameter("pas");
        String loginError = Validator.isLoginValid(login);
        String passwordError = Validator.isPasswordValid(password);
        if (loginError != null) {
            req.setAttribute("loginError", loginError);
            return new View("login", false);
        }
        if (passwordError != null) {
            req.setAttribute("passwordError", passwordError);
            return new View("login", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            SystemMemberDao systemMemberDao = daoFactory.newSystemMemberDao();
            SystemMember systemMember = systemMemberDao.findByCredentials(login, password);
            if (systemMember == null) {
                req.setAttribute("authError", "auth.error");
                return new View("login", false);
            }
            req.getSession().setAttribute("systemMember",systemMember);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("user", true);
    }
}
