package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.SystemMemberDao;
import com.epam.irasov.filmlibrary.entity.SystemMember;
import com.epam.irasov.filmlibrary.logic.Operation;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserPasswordAction implements Action {
    private static final String PASSWORD_MISMATCH = "pass.mismatch";

    public EditUserPasswordAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String password = req.getParameter("previousPass");
        String newPassword = req.getParameter("pass");
        SystemMember systemMember = (SystemMember) req.getSession().getAttribute("systemMember");
        String passwordError = Validator.isPasswordValid(password);
        if (passwordError != null) {
            req.setAttribute("passwordError", passwordError);
            return new View("change-password", false);
        }
        passwordError = Validator.isPasswordValid(newPassword);
        if (passwordError != null) {
            req.setAttribute("passwordError", passwordError);
            return new View("change-password", false);
        }
        newPassword = Operation.getMD5(newPassword);
        System.out.println(newPassword);
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            SystemMemberDao systemMemberDao = daoFactory.newSystemMemberDao();
            if (systemMemberDao.FindPassword(systemMember.getId(), Operation.getMD5(password))){
                req.setAttribute("passwordPreviousError", PASSWORD_MISMATCH);
                daoFactory.endTx();
                return new View("change-password", false);
            }
            systemMemberDao.UpDatePassword(systemMember.getId(), newPassword);
            systemMember.setPassword(newPassword);
            req.getSession().setAttribute("systemMember", systemMember);
            req.getSession().setAttribute("messageEditPass", "edit.user.pass");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("change-password", true);
    }
}
