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

public class AddNewsAction implements Action {
    private final static Long ID_NEWS= 1l;
    private static final String PATH_PHOTO = "img/site/";

    public AddNewsAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String date = req.getParameter("date");
        String imageNews = req.getParameter("fileName");
        String textError = Validator.isTextValid(text);
        if (textError != null) {
            req.setAttribute("textError", textError);
            req.setAttribute("fileName", imageNews);
            return new View("operation-with-news", false);
        }
        DaoFactory daoFactory = DaoFactory.getInstance();
        try {
            daoFactory.beginTx();
            NewsDao newsDao = daoFactory.newNewsDao();
            newsDao.save(new News(ID_NEWS, title, LocalDate.parse(date, ofPattern("yyyy-MM-dd")), text, PATH_PHOTO+imageNews));
            req.setAttribute("messageNews", "news.add.complete");
            req.getSession().setAttribute("selectedAction", "");
            daoFactory.endTx();
        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            daoFactory.close();
        }
        return new View("operation-with-news", false);
    }
}
