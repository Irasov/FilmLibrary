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
    }

    public Action getAction(HttpServletRequest request) {
        String method = request.getMethod();
        String action = request.getParameter("action");
        return actions.get(method+"/"+action);
    }
}
