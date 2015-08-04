package com.epam.irasov.filmlibrary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationAction implements Action{
    public RegistrationAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view = new View("home",true);
        return view;
    }
}
