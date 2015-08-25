package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.logic.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CheckFilmMembersAction implements Action {
    public CheckFilmMembersAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            if (filmMemberDao.emptyTable()) {
                req.getSession().setAttribute("filmMembersView", "");
                return new View("film-members", true);
            }
            List<FilmMember> members = filmMemberDao.selectFilmMember();
            Operation.sortFilmMember(members, Operation.SORT_NAME);
            req.getSession().setAttribute("filmMembersView", members);
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("film-members", true);
    }
}
