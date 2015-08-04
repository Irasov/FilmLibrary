package com.epam.irasov.filmlibrary.action;

public class View {
    String path;
    boolean redirect;

    public View(String path, boolean redirect) {
        this.path = path;
        this.redirect = redirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean getRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}
