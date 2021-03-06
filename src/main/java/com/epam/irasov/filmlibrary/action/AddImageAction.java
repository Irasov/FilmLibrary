package com.epam.irasov.filmlibrary.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

public class AddImageAction implements Action {
    private static final String UPLOAD_DIR = "img/site";

    public AddImageAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            boolean mkDirs = fileSaveDir.mkdirs();
        }
        String fileName = null;
        try {
            for (Part part : req.getParts()) {
                fileName = getFileName(part);
                if(!fileName.equals("")) part.write(uploadFilePath + File.separator + fileName);
            }
        } catch (IOException | ServletException e) {
            req.setAttribute("fileError", "file.null");
            return new View(req.getParameter("page"), false);
        }
        req.setAttribute("fileName", fileName);
        return new View(req.getParameter("page"), false);
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
