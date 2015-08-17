package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.logic.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CheckFilmMembers implements Action {
    private static final String SORT_CRITERION = "name";
    public CheckFilmMembers() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
            if (filmMemberDao.emptyTable()) {
                return new View("film-members", true);
            }
            List<FilmMember> members = filmMemberDao.selectFilmMember();
            Operation.sortFilmMember(members, SORT_CRITERION);
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
