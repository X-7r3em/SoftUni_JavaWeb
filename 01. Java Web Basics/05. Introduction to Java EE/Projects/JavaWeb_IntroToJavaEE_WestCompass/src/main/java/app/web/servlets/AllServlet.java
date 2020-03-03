package app.web.servlets;

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

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    private static final String ALL_FILE_PATH = "D:\\SoftUni\\07. Java Web\\01. Java Web Basics\\05. Introduction to Java EE\\Projects\\West Compass\\src\\main\\webapp\\views\\all.html";

    private final FileUtil fileUtil;
    private final CarService carService;

    @Inject
    public AllServlet(FileUtil fileUtil, CarService carService) {
        this.fileUtil = fileUtil;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = fileUtil.readFile(ALL_FILE_PATH);
        PrintWriter out = resp.getWriter();
        StringBuilder presentCars = new StringBuilder();
        this.carService.getCars().forEach(c -> presentCars.append(c).append(System.lineSeparator()));
        html = html.replace("{{replace}}", presentCars.toString());
        out.println(html);
    }
}
