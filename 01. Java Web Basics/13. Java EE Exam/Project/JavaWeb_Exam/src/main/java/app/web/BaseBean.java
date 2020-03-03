package app.web;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class BaseBean {
    protected void redirect(String url) {
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("/faces/views" + url + ".xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getHeroId() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        return req.getParameter("id");
    }
}
