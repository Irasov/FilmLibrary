package com.epam.irasov.filmlibrary.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class AddMemberPhotoAction implements Action {
    private static final String UPLOAD_PHOTO_DIR = "img/site";

    public AddMemberPhotoAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_PHOTO_DIR;
        System.out.println(uploadFilePath+File.separator+"file");
        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            boolean mkDirs = fileSaveDir.mkdirs();
        }
        String fileName = null;
        try {
            for (Part part : req.getParts()) {
                fileName = getFileName(part);
                if (!fileName.equals("")) part.write(uploadFilePath + File.separator + fileName);
            }
        } catch (IOException | ServletException e) {
            req.setAttribute("fileError", "file.null");
            return new View("operation-with-members-film", false);
        }
        req.setAttribute("fileName", fileName);
        return new View("operation-with-members-film", false);
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