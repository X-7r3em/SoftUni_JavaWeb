package web;

import javax.faces.context.FacesContext;
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
}
