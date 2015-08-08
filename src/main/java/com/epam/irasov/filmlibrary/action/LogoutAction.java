package com.epam.irasov.filmlibrary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {

    public LogoutAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return new View("index",true);
    }
}
