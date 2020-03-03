package web;

import models.CourseServiceModel;
import services.CourseService;
import services.HtmlService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    private final CourseService courseService;
    private final HtmlService htmlService;

    @Inject
    public CourseServlet(CourseService courseService, HtmlService htmlService) {
        this.courseService = courseService;
        this.htmlService = htmlService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CourseServiceModel> courses = courseService.getAllCourses();
        String body =
                htmlService.getCreateCoursesForm() +
                htmlService.getCoursesList(courses);
        resp.setContentType("text/html");
        resp.getWriter().println(body);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String course = req.getParameter("name");
        courseService.createCourse(course);
        resp.sendRedirect("/courses");
    }
}
