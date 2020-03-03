package web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LogoutBean extends BaseBean {
    public void logout() {
        endSession();
        redirect("/index");
    }
}
