package controller;

import model.Course;
import model.Teacher;
import repository.CourseFileRepository;
import repository.StudentFileRepository;
import repository.TeacherFileRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CourseController {

    private CourseFileRepository courseFileRepository;
    private StudentFileRepository studentFileRepository;
    private TeacherFileRepository teacherFileRepository;

    public CourseController(CourseFileRepository courseFileRepository, StudentFileRepository studentFileRepository, TeacherFileRepository teacherFileRepository) {
        this.courseFileRepository = courseFileRepository;
        this.studentFileRepository = studentFileRepository;
        this.teacherFileRepository = teacherFileRepository;
    }

    public CourseFileRepository getCourseFileRepository() {
        return courseFileRepository;
    }

    public void setCourseFileRepository(CourseFileRepository courseFileRepository) {
        this.courseFileRepository = courseFileRepository;
    }

    public StudentFileRepository getStudentFileRepository() {
        return studentFileRepository;
    }

    public void setStudentFileRepository(StudentFileRepository studentFileRepository) {
        this.studentFileRepository = studentFileRepository;
    }

    public TeacherFileRepository getTeacherFileRepository() {
        return teacherFileRepository;
    }

    public void setTeacherFileRepository(TeacherFileRepository teacherFileRepository) {
        this.teacherFileRepository = teacherFileRepository;
    }

    public Course addCourse(Course course) throws IOException {
        List<Teacher> teachers = this.teacherFileRepository.getAll();
        boolean exists = false;

        for (Teacher teacher : teachers) {
            List<Long> coursesIds = teacher.getCoursesIds();
            for (Long courseId : coursesIds) {
                if (teacher.getPersonId() == course.getTeacherId() && courseId != course.getCourseId()) {
                    this.courseFileRepository.create(course);
                    courseFileRepository.writeDataToFile();
                    teacherFileRepository.writeDataToFile();
                }
            }
        }

        return this.courseFileRepository.create(course);
    }

    public List<Course> getAllCourses() {
        return this.courseFileRepository.getAll();
    }

    public Course updateCourse(Course course) throws IOException {
        this.courseFileRepository.update(course);
        this.courseFileRepository.writeDataToFile();
        return course;
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
