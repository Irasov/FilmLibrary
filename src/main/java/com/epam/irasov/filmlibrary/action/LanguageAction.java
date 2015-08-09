package com.epam.irasov.filmlibrary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LanguageAction implements Action {
    public LanguageAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String locale = req.getParameter("lang");
        System.out.println(locale);
        req.getSession().setAttribute("locale",locale);
        return new View("index",true);
    }
}
