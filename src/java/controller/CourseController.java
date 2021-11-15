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

    public void writeDataToCourseFile() throws IOException {
        this.courseFileRepository.writeDataToFile();
    }

    public Course addCourse(Course course) throws IOException {
        this.courseFileRepository.create(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return this.courseFileRepository.getAll();
    }

    public Course updateCourse(Course course) throws IOException {
        return this.courseFileRepository.update(course);
    }

    public void deleteCourse(Course course) throws IOException {
        this.courseFileRepository.delete(course);
    }

    /**
     * sorts all courses by credits
     *
     * @return a sorted list of courses
     */
    public List<Course> sortCoursesByCredits() {
        List<Course> sortedCourses = this.courseFileRepository.getAll();
        Comparator<Course> compareByCredits = Course::compareTo;
        sortedCourses.sort(compareByCredits);
        return sortedCourses;
    }

    /**
     * filters all courses by credits
     *
     * @param credits is number of credits to be compared with
     * @return a filtered list of courses
     */
    public List<Course> filterCoursesByCredits(int credits) {
        return this.courseFileRepository.getAll().stream().filter(course -> course.getCredits() >= credits).collect(Collectors.toList());
    }
}
