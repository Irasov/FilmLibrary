package com.epam.irasov.filmlibrary.servlet;

import com.epam.irasov.filmlibrary.action.Action;
import com.epam.irasov.filmlibrary.action.ActionFactory;
import com.epam.irasov.filmlibrary.action.View;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontControllerServlet", urlPatterns = "/controller")
public class FrontControllerServlet extends HttpServlet {

    private ActionFactory factory;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = factory.getAction(req);
        if(action==null){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        View view = action.execute(req,resp);
        if(view.getRedirect()){
            resp.sendRedirect(view.getPath() + ".jsp");
        }else{
            req.getRequestDispatcher("/"+"index" + ".jsp").forward(req, resp);
        }
    }

    @Override
     public void init() throws ServletException {
        factory = new ActionFactory();
    }
}
