package services;

import models.CourseServiceModel;

import java.util.List;
import java.util.function.Function;

public class HtmlServiceImpl implements HtmlService {
    private final Function<CourseServiceModel, String> coursesItemFunc = c -> String.format("<li>%s</li>", c.getName());

    @Override
    public String getCoursesList(List<CourseServiceModel> courses) {
        return this.getList(courses, this.coursesItemFunc);
    }

    @Override
    public String getCreateCoursesForm() {
        return "<form method='post' action='/courses'>" +
                "   <label>" +
                "       Name:" +
                "       <input name='name' type='text' />" +
                "       <button>Create</button>" +
                "   </label>" +
                "</form>";
    }

    private <T> String getList(List<T> collection, Function<T, String> itemFunc) {
        StringBuilder output = new StringBuilder();
        collection.forEach(item ->
                output.append(itemFunc.apply(item))
        );
        return String.format("<ul>%s</ul>", output.toString());
    }
}
