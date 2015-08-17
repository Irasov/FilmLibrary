package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.FilmMemberDao;
import com.epam.irasov.filmlibrary.entity.FilmMember;
import com.epam.irasov.filmlibrary.logic.Operation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectedActionMemberAction implements Action {
    private static final int ADD_MEMBER = 1;
    private final static int EDIT_MEMBER = 2;
    private static final int REMOVE_FILM_MEMBER = 3;
    private static final String MESSAGE_ERROR = "film.member.null";

    public SelectedActionMemberAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if (Integer.parseInt(selected) == ADD_MEMBER) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                daoFactory.beginTx();
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
        if(Integer.parseInt(selected) == EDIT_MEMBER){
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
                if (filmMemberDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", EDIT_MEMBER);
                    req.getSession().setAttribute("messageError", MESSAGE_ERROR);
                    return new View("operation-with-members-film", false);
                }
                daoFactory.beginTx();
                List<FilmMember> filmMembers = filmMemberDao.selectFilmMember();
                Operation.sortFilmMember(filmMembers, Operation.SORT_NAME);
                req.getSession().setAttribute("filmMembers", filmMembers);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            req.getSession().setAttribute("selectedAction", EDIT_MEMBER);
            return new View("operation-with-members-film", false);
        }
        if (Integer.parseInt(selected) == REMOVE_FILM_MEMBER) {
            req.getSession().setAttribute("selectedAction", REMOVE_FILM_MEMBER);
            DaoFactory daoFactory = DaoFactory.getInstance();
            try {
                FilmMemberDao filmMemberDao = daoFactory.newFilmMemberDao();
                if (filmMemberDao.emptyTable()) {
                    req.getSession().setAttribute("selectedAction", REMOVE_FILM_MEMBER);
                    req.getSession().setAttribute("messageError", MESSAGE_ERROR);
                    return new View("operation-with-members-film", false);
                }
                List<FilmMember> filmMembers = filmMemberDao.selectFilmMember();
                Operation.sortFilmMember(filmMembers, Operation.SORT_NAME);
                req.getSession().setAttribute("filmMembers", filmMembers);
                daoFactory.endTx();
            } catch (Exception e) {
                throw new DaoException(e);
            } finally {
                daoFactory.close();
            }
            return new View("operation-with-members-film", false);
        }
        return new View("operation-with-members-film", false);
    }
}
