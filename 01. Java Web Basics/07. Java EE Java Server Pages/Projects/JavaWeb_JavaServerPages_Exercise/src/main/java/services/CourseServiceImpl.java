package services;

import models.CourseServiceModel;
import models.entities.Course;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {
    private static final String FIND_ALL_QUERY = "FROM Course";

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Inject
    public CourseServiceImpl(EntityManager entityManager, ModelMapper modelMapper) {
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseServiceModel> getAllCourses() {
        List<Course> courses = entityManager.createQuery(FIND_ALL_QUERY, Course.class).getResultList();
        return courses.stream()
                .map(c -> modelMapper.map(c, CourseServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createCourse(String courseName) {
        Course course = new Course();
        course.setName(courseName);
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
    }
}
