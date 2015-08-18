package com.epam.irasov.filmlibrary.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class EditPhotoUserAction implements Action {
    private static final String UPLOAD_DIR_PHOTO_MEMBER = "img/site";

    public EditPhotoUserAction() {
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR_PHOTO_MEMBER;
        File fileSaveDir = new File(uploadFilePath);
        String star = req.getParameter("strar");
        System.out.println("SSSSSSSSSSSS:"+star);
        if ((!(star == null))&&(!(star.equals(UPLOAD_DIR_PHOTO_MEMBER+"/no_avatar.png")))) {
            File file = new File(applicationPath + star);
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
                if (!fileName.equals("")) {
                    part.write(uploadFilePath + File.separator + fileName);
                    imgName = fileName;
                }
            }
        } catch (IOException | ServletException e) {
            req.setAttribute("fileError", "file.null");
            return new View("edit-personal-data", false);
        }
        req.setAttribute("fileName", imgName);
        return new View("edit-personal-data", false);
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
