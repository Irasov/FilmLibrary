package com.epam.irasov.filmlibrary.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectedActionFilmAction implements Action{
    private final static int ADD_FILM = 1;
    private final static int EDIT_FILMS = 2;
    private final static int FILM_ADD_MEMBER = 3;
    private final static int FILM_ADD_GENRE = 4;
    private final static int REMOVE_FILM = 5;

    public SelectedActionFilmAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String selected = req.getParameter("selected");
        if(Integer.parseInt(selected)==ADD_FILM){
            req.getSession().setAttribute("selectedAction",ADD_FILM);
            return new View("operation-with-movies",false);
        }
        if(Integer.parseInt(selected)==EDIT_FILMS){
            req.getSession().setAttribute("selectedAction",EDIT_FILMS);
            return new View("operation-with-movies",false);
        }
        if(Integer.parseInt(selected)==FILM_ADD_MEMBER){
            req.getSession().setAttribute("selectedAction",FILM_ADD_MEMBER);
            return new View("operation-with-movies",false);
        }
        if(Integer.parseInt(selected)==FILM_ADD_GENRE){
            req.getSession().setAttribute("selectedAction",FILM_ADD_GENRE);
            return new View("operation-with-movies",false);
        }
        if(Integer.parseInt(selected)==REMOVE_FILM){
            req.getSession().setAttribute("selectedAction",REMOVE_FILM);
            return new View("operation-with-movies",false);
        }
        return new View("operation-with-movies",false);
    }
}
