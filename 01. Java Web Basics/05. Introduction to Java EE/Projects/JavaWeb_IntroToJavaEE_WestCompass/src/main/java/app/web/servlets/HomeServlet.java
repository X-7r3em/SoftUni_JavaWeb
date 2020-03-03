package app.web.servlets;

import app.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private static final String HOME_FILE_PATH = "D:\\SoftUni\\07. Java Web\\01. Java Web Basics\\05. Introduction to Java EE\\Projects\\West Compass\\src\\main\\webapp\\views\\home.html";
    private final FileUtil fileUtil;

    @Inject
    public HomeServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = fileUtil.readFile(HOME_FILE_PATH);
        PrintWriter out = resp.getWriter();
        out.println(html);
    }
}
