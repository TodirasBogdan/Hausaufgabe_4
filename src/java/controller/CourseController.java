package controller;

import model.Course;
import repository.CourseFileRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CourseController {

    private CourseFileRepository courseFileRepository;

    public CourseController() {
    }

    public CourseController(CourseFileRepository courseFileRepository) {
        this.courseFileRepository = courseFileRepository;
    }

    public CourseFileRepository getCourseFileRepository() {
        return courseFileRepository;
    }

    public void setCourseFileRepository(CourseFileRepository courseFileRepository) {
        this.courseFileRepository = courseFileRepository;
    }

    public void readDataFromCourseFile() throws IOException {
        this.courseFileRepository.readDataFromFile();
    }

    public void writeToCourseFile() {
        this.courseFileRepository.writeDataToFile();
    }

    public Course addCourse(Course course) {
        this.courseFileRepository.create(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return this.courseFileRepository.getAll();
    }

    public Course updateCourse(Course course) {
        return this.courseFileRepository.update(course);
    }

    public void deleteCourse(Course course) {
        this.courseFileRepository.delete(course);
    }

    /**
     * sorts all courses by credits
     */
    public List<Course> sortCoursesByCredits() {
        List<Course> sortedCourses = this.courseFileRepository.getAll();
        Comparator<Course> compareByCredits = Course::compareTo;
        sortedCourses.sort(compareByCredits);
        return sortedCourses;
    }

    /**
     * filters all courses by credits
     */
    public List<Course> filterCoursesByCredits(int credits) {
        return this.courseFileRepository.getAll().stream().filter(course -> course.getCredits() >= credits).collect(Collectors.toList());
    }
}
