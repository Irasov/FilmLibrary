package com.epam.irasov.filmlibrary.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class EditImageAction implements Action {
    private static final String UPLOAD_DIR = "img/site";
    private static final String PHOTO_DEFAULT = "img/site/no_avatar.png";

    public EditImageAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File fileSaveDir = new File(uploadFilePath);
        String previousImage = req.getParameter("strar");
        System.out.println("sssss "+previousImage);
        if (!(previousImage == null)&&(!previousImage.equals(PHOTO_DEFAULT))) {
            File file = new File(applicationPath +previousImage);
            boolean res = file.delete();
        }
        if (!fileSaveDir.exists()) {
            boolean mkDirs = fileSaveDir.mkdirs();
        }
        String fileName;
        String imgName = null;
        try {
            for (Part part : req.getParts()) {
                fileName = getFileName(part);
                System.out.println(fileName);
                if (!fileName.equals("")) {
                    part.write(uploadFilePath + File.separator + fileName);
                    imgName = fileName;
                }
            }
        } catch (IOException | ServletException e) {
            req.setAttribute("fileError", "file.null");
            return new View(req.getParameter("page"), false);
        }
        req.setAttribute("fileName", imgName);
        return new View(req.getParameter("page"), false);
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println(part.getHeader("content-disposition"));
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}