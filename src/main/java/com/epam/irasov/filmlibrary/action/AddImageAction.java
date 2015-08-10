package com.epam.irasov.filmlibrary.action;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

public class AddImageAction implements Action {
    private static final String UPLOAD_DIR = "img";
    public AddImageAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        String fileName = null;
        try {
            for (Part part : req.getParts()) {
                fileName = getFileName(part);
                part.write(uploadFilePath + File.separator + fileName);
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        req.setAttribute("fileName", fileName);
        return new View("operation-with-movies",false);
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
