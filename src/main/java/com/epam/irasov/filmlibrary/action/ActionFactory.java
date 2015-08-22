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
        actions.put("POST/selectFilm", new SelectFilmAction());
        actions.put("POST/editFilm", new EditFilmAction());
        actions.put("GET/editFilm", new EditFilmAction());
        actions.put("POST/editImage", new EditImageAction());
        actions.put("POST/removeFilm", new RemoveFilmAction());
        actions.put("POST/selectedMemberOperation", new SelectedActionMemberAction());
        actions.put("POST/addMember", new AddMemberAction());
        actions.put("POST/selectMember", new SelectMemberAction());
        actions.put("POST/editFilmMember", new EditFilmMemberAction());
        actions.put("GET/editFilmMember", new EditFilmMemberAction());
        actions.put("POST/removeFilmMember", new RemoveFilmMemberAction());
        actions.put("POST/memberAddFilm", new AddFilmMembersAction());
        actions.put("POST/selectFilmFilmMember", new SelectFilmFilmMemberAction());
        actions.put("POST/removeFilmFilmMember", new RemoveFilmFilmMemberAction());
        actions.put("GET/checkFilms", new CheckFilmsAction());
        actions.put("POST/selectFilmView", new SelectFilmViewAction());
        actions.put("POST/selectMemberView", new SelectMemberViewAction());
        actions.put("GET/checkFilmMembers", new CheckFilmMembersAction());
        actions.put("GET/editUser", new EditUserAction());
        actions.put("POST/editUser", new EditUserAction());
        actions.put("POST/editUserPassword", new EditUserPasswordAction());
        actions.put("POST/selectedNewsOperation", new SelectedActionNewsAction());
        actions.put("POST/addNews", new AddNewsAction());
        actions.put("POST/selectNews", new SelectNewsAction());
        actions.put("GET/editNews", new EditNewsAction());
        actions.put("POST/editNews", new EditNewsAction());
        actions.put("POST/removeNews", new RemoveNewsAction());
        actions.put("GET/checkNews", new CheckNewsAction());
        actions.put("POST/selectNewsView", new SelectNewsViewAction());
        actions.put("POST/selectedOperationNewsBlock", new SelectedActionNewsBlockAction());
        actions.put("POST/blockAddNews", new BlockAddNewsAction());
        actions.put("POST/blockRemoveNews", new RemoveNewsBlockAction());
        actions.put("POST/selectedOperationFilmBlock", new SelectedActionFilmBlockAction());
        actions.put("POST/blockAddFilm", new BlockAddFilmAction());
        actions.put("POST/blockRemoveFilm", new BlockRemoveFilmAction());
        actions.put("POST/incVotes", new IncVotesAction());
        actions.put("POST/addReviews", new AddReviewsAction());
    }

    public Action getAction(HttpServletRequest request) {
        String method = request.getMethod();
        String action = request.getParameter("action");
        System.out.println(action+method);
        return actions.get(method+"/"+action);
    }
}
