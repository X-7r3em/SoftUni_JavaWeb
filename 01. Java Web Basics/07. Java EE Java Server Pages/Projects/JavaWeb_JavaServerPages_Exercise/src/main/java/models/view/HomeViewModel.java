package models.view;

import models.CourseServiceModel;

import java.time.LocalDate;
import java.util.List;

public class HomeViewModel {
    private String name;
    private LocalDate createdOn;
    private List<CourseServiceModel> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public List<CourseServiceModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseServiceModel> courses) {
        this.courses = courses;
    }
}
