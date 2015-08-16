package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class RemoveFilmMemberAction implements Action {
    private static final int ADD_MEMBER = 1;
    public RemoveFilmMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long selectFilmMember = Long.parseLong(req.getParameter("idFilmMember").substring(0, req.getParameter("idFilmMember").indexOf("+")));
        String photo = req.getParameter("idFilmMember").substring(req.getParameter("idFilmMember").indexOf("+") + 1, req.getParameter("idFilmMember").length());
        String applicationPath = req.getServletContext().getRealPath("");
        File file = new File(applicationPath + photo);
        boolean res = file.delete();
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            filmMemberDao.remove(selectFilmMember);
            req.getSession().setAttribute("selectedAction", ADD_MEMBER);
            req.getSession().setAttribute("message", "remove.message");
            req.getSession().setAttribute("filmMembers", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-members-film", true);
    }
}
