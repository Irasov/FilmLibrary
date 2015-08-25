package com.epam.irasov.filmlibrary.action;

import com.epam.irasov.filmlibrary.util.PropertyManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
private static final  String PROPERTIES_FILE = "action.properties";
    private static Logger LOGGER = Logger.getLogger(ActionFactory.class);
    private Map<String, Action> actions;

    public ActionFactory() {
        PropertyManager propertyManager = new PropertyManager();
        propertyManager.loadProperty(PROPERTIES_FILE);
        actions = propertyManager.getPropertyKey();
    }

    public Action getAction(HttpServletRequest request) {
        String method = request.getMethod();
        String action = request.getParameter("action");
        LOGGER.info(action+method);
        return actions.get(method+"/"+action);
    }
}
