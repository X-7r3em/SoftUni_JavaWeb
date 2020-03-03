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
public class FriendBean extends BaseBean {
    private List<UserViewModel> friends;

    private UserService userService;
    private ModelMapper modelMapper;

    public FriendBean() {
    }

    @Inject
    public FriendBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.friends = this.getSessionUser().getFriends()
                .stream()
                .map(f -> this.modelMapper.map(f, UserViewModel.class))
                .collect(Collectors.toList());
    }

    public void removeFriend(String friendUsername) {
        UserServiceModel friend = this.userService.getByUsername(friendUsername);
        UserServiceModel user = this.getSessionUser();

        friend.getFriends().remove(user);
        user.getFriends().remove(friend);

        this.userService.update(user);
        this.userService.update(friend);

        this.redirect("/friends");
    }

    public List<UserViewModel> getFriends() {
        return friends;
    }

    public void setFriends(List<UserViewModel> friends) {
        this.friends = friends;
    }

    private UserServiceModel getSessionUser() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        String id = req.getSession().getAttribute("userId").toString();
        return this.userService.getById(id);
    }
}
