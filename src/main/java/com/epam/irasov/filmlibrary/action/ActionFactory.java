package com.epam.irasov.filmlibrary.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    private Map<String, Action> actions;

    public ActionFactory() {
        actions = new HashMap<>();
        actions.put("POST/register", new RegistrationAction());
        actions.put("GET/start", new StartAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/lang", new LanguageAction());
        actions.put("POST/editPersonalData", new LanguageAction());
        actions.put("POST/addFilm", new AddFilmAction());
        actions.put("POST/addImage", new AddImageAction());
        actions.put("POST/selected", new SelectedActionFilmAction());
        actions.put("POST/selectFilm", new SelectFilm());
        actions.put("POST/editFilm", new EditFilmAction());
    }

    public Action getAction(HttpServletRequest request) {
        String method = request.getMethod();
        String action = request.getParameter("action");
        System.out.println(action+method);
        return actions.get(method+"/"+action);
    }
}
