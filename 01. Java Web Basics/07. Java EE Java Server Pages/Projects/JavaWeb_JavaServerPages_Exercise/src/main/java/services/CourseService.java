package services;

import models.CourseServiceModel;

import java.util.List;

public interface CourseService {
    List<CourseServiceModel> getAllCourses();

    void createCourse(String courseName);
}
