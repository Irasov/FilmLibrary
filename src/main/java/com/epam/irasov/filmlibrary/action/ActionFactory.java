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
        actions.put("GET/editFilm", new EditFilmAction());
        actions.put("POST/editImage", new EditImageAction());
        actions.put("POST/removeFilm", new RemoveFilmAction());
        actions.put("POST/selectedMemberOperation", new SelectedActionMemberAction());
        actions.put("POST/addPhoto", new AddMemberPhotoAction());
        actions.put("POST/addMember", new AddMemberAction());
        actions.put("POST/selectMember", new SelectMemberAction());
        actions.put("POST/editPhotoMember", new EditPhotoMemberAction());
        actions.put("POST/editFilmMember", new EditFilmMemberAction());
        actions.put("GET/editFilmMember", new EditFilmMemberAction());
        actions.put("POST/removeFilmMember", new RemoveFilmMemberAction());
        actions.put("POST/memberAddFilm", new AddFilmMembersAction());
        actions.put("POST/selectFilmFilmMember", new SelectFilmFilmMemberAction());
        actions.put("POST/removeFilmFilmMember", new RemoveFilmFilmMember());
    }

    public Action getAction(HttpServletRequest request) {
        String method = request.getMethod();
        String action = request.getParameter("action");
        System.out.println(action+method);
        return actions.get(method+"/"+action);
    }
}
