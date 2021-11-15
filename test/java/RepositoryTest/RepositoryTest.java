package RepositoryTest;

import model.Course;
import model.Teacher;
import org.junit.jupiter.api.Test;
import repository.CourseRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * check if all repository functions work properly
 */
public class RepositoryTest {

    private final CourseRepository courseRepository = new CourseRepository();
    List<Long> coursesIds = new ArrayList<>();
    private final Teacher teacher = new Teacher("Ion", "Ionescu", 789, coursesIds);
    List<Course> courses = new ArrayList<>();
    List<Long> studentsIds = new ArrayList<>();
    private final Course course1 = new Course("Map", teacher.toString(), 30, studentsIds, 5, 123456);
    private final Course course2 = new Course("Fp", teacher.toString(), 30, studentsIds, 7, 654321);
    private final Course course3 = new Course("Map", teacher.toString(), 30, studentsIds, 6, 123456);

    /**
     * check if create works properly
     */
    @Test
    public void testCreate() throws IOException {
        assert (courseRepository.getAll().size() == 0);
        assert (courseRepository.create(course1) == course1);
        assert (courseRepository.getAll().size() == 1);
        courses = courseRepository.getAll();
        assert (courses.get(0).equals(course1));
    }

    /**
     * check if getAll works properly
     */
    @Test
    public void testGetAll() throws IOException {
        assert (courseRepository.getAll().size() == 0);
        courseRepository.create(course1);
        assert (courseRepository.getAll().size() == 1);
        courses = courseRepository.getAll();
        assert (courses.get(0).equals(course1));
        courseRepository.create(course2);
        assert (courseRepository.getAll().size() == 2);
        courses = courseRepository.getAll();
        assert (courses.get(0).equals(course1));
        assert (courses.get(1).equals(course2));
    }

    /**
     * check if update works properly
     */
    @Test
    public void testUpdate() throws IOException {
        assert (courseRepository.getAll().size() == 0);
        courseRepository.create(course1);
        assert (courseRepository.getAll().size() == 1);
        courses = courseRepository.getAll();
        assert (courses.get(0).equals(course1));
        assert (course2.equals(courseRepository.update(course2)));
        courseRepository.update(course3);
        assert (courseRepository.getAll().size() == 1);
        courses = courseRepository.getAll();
        assert (courses.get(0).equals(course3));
    }

    /**
     * check if delete works properly
     */
    @Test
    public void testDelete() throws IOException {
        assert (courseRepository.getAll().size() == 0);
        courseRepository.create(course1);
        assert (courseRepository.getAll().size() == 1);
        courses = courseRepository.getAll();
        assert (courses.get(0).equals(course1));
        courseRepository.delete(course2);
        assert (courseRepository.getAll().size() == 1);
        courses = courseRepository.getAll();
        assert (courses.get(0).equals(course1));
        courseRepository.delete(course1);
        assert (courseRepository.getAll().size() == 0);
    }

}
