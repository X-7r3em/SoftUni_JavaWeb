package services;

import models.CourseServiceModel;

import java.util.List;

public interface HtmlService {
    String getCoursesList(List<CourseServiceModel> courses);

    String getCreateCoursesForm();
}
