package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedNewsAction implements Action {
    private static final int ADD_NEWS = 1;

    public SelectedNewsAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == ADD_NEWS) {
            req.getSession().setAttribute("selectedAction", ADD_NEWS);
            return new View("operation-with-news", false);
        }
        return new View("operation-with-news", false);
    }
}
