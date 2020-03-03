package web;


import domain.model.service.UserServiceModel;
import domain.model.view.UserViewModel;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {
    private List<UserViewModel> users;

    private UserService userService;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        UserServiceModel userDb = this.getSessionUser();
        List<UserServiceModel> usersDb = this.userService.getAll()
                .stream()
                .filter(u -> !userDb.getFriends().contains(u))
                .filter(u -> !u.getId().equals(userDb.getId()))
                .collect(Collectors.toList());
        this.users = usersDb.stream()
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());
    }

    public void addFriend(String friendUsername) {
        UserServiceModel friend = this.userService.getByUsername(friendUsername);
        UserServiceModel user = this.getSessionUser();

        friend.getFriends().add(user);
        user.getFriends().add(friend);

        this.userService.update(user);
        this.userService.update(friend);

        this.redirect("/home");
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }

    private UserServiceModel getSessionUser() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        String id = req.getSession().getAttribute("userId").toString();
        return this.userService.getById(id);
    }
}
