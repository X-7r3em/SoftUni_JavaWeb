package app.web.servlets;

import app.domain.models.service.CarServiceModel;
import app.service.CarService;
import app.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final String CREATE_FILE_PATH = "D:\\SoftUni\\07. Java Web\\01. Java Web Basics\\05. Introduction to Java EE\\Projects\\West Compass\\src\\main\\webapp\\views\\create.html";

    private final FileUtil fileUtil;
    private final CarService carService;

    @Inject
    public CreateServlet(FileUtil fileUtil, CarService carService) {
        this.fileUtil = fileUtil;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(CREATE_FILE_PATH);
        PrintWriter out = resp.getWriter();
        out.println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarServiceModel car = new CarServiceModel();
        car.setBrand(req.getParameter("brand"));
        car.setModel(req.getParameter("model"));
        car.setYear(Integer.parseInt(req.getParameter("year")));
        car.setEngine(req.getParameter("engine"));
        this.carService.createCar(car);
        resp.sendRedirect("/all");
    }
}
