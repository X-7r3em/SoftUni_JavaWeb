package web;

import javax.faces.context.FacesContext;

public class BaseBean {
    protected void redirect(String url) {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("/faces/view" + url + ".xhtml");
        } catch (Exception ignored) {
        }
    }

    protected void startSession(String username) {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .put("username", username);
    }

    protected void endSession() {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();
    }
}
