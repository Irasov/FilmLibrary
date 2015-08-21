package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.dao.DaoException;
import com.epam.irasov.filmlibrary.dao.DaoFactory;
import com.epam.irasov.filmlibrary.dao.NewsDao;
import com.epam.irasov.filmlibrary.entity.News;
import com.epam.irasov.filmlibrary.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;

public class EditNewsAction implements Action {
    public EditNewsAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String date = req.getParameter("date");
        String image = req.getParameter("image");
        String textError = Validator.isTextValid(text);
        if (textError != null) {
            req.setAttribute("textError", textError);
            req.setAttribute("fileName", image);
            return new View("operation-with-news", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            NewsDao newsDao = daoFactory.newNewsDao();
            newsDao.upDate(new News(id, title, LocalDate.parse(date, ofPattern("yyyy-MM-dd")), text, image));
            req.setAttribute("messageNews", "edit.message");
            req.getSession().setAttribute("selectedAction", "");
            req.getSession().setAttribute("item", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-news", false);
    }
}
