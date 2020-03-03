package web;

import models.view.HomeViewModel;
import services.CourseService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private final CourseService courseService;

    @Inject
    public HomeServlet(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", "Pesho");
        HomeViewModel hvm = new HomeViewModel();
        hvm.setName("Popo");
        hvm.setCreatedOn(LocalDate.now());
        hvm.setCourses(courseService.getAllCourses());
        req.setAttribute("viewModel", hvm);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
