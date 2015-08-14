package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedActionMemberAction implements Action {
    private static final int ADD_MEMBER = 1;

    public SelectedActionMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == ADD_MEMBER) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
                List<FilmMember.Type> types = filmMemberDao.select();
                req.getSession().setAttribute("types", types);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", ADD_MEMBER);
            return new View("operation-with-members-film", false);
        }
        return new View("operation-with-members-film", false);
    }
}
