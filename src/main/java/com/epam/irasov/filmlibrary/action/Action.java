package com.epam.irasov.filmlibrary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    View execute(HttpServletRequest req, HttpServletResponse resp);
}
