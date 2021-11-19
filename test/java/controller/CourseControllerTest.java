package controller;

import model.Course;
import model.Student;
import model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CourseFileRepository;
import repository.StudentFileRepository;
import repository.TeacherFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseControllerTest {

    CourseFileRepository courseFileRepository = new CourseFileRepository("./test/resources/testCourses.json");
    StudentFileRepository studentFileRepository = new StudentFileRepository("./test/resources/testStudents.json");
    TeacherFileRepository teacherFileRepository = new TeacherFileRepository("./test/resources/testTeachers.json");

    CourseController courseController = new CourseController(courseFileRepository, studentFileRepository, teacherFileRepository);

    List<Long> coursesIds = new ArrayList<>();
    private final Teacher teacher = new Teacher("Ion", "Ionescu", 789, coursesIds);
    List<Long> studentsIds = new ArrayList<>();
    private final Course course1 = new Course("Map", teacher.getPersonId(), 30, studentsIds, 5, 123456);
    private final Course course2 = new Course("Fp", teacher.getPersonId(), 30, studentsIds, 7, 654321);
    private final Course course3 = new Course("Map", teacher.getPersonId(), 30, studentsIds, 6, 123456);

    /**
     * sets the testCourses file
     */
    @BeforeEach
    public void setCourseFile() throws IOException {
        courseFileRepository.getAll().clear();
        studentFileRepository.getAll().clear();
        teacherFileRepository.getAll().clear();
        courseFileRepository.create(new Course("Geometrie", 10L, 20, new ArrayList<>(Arrays.asList(1L, 2L)), 6, 100));
        courseFileRepository.create(new Course("Algebra", 11L, 25, new ArrayList<>(List.of(2L)), 5, 101));
        studentFileRepository.create(new Student("Vlad", "Nechifor", 1L, 1L, 0, new ArrayList<>(Arrays.asList(100L, 101L))));
        studentFileRepository.create(new Student("Tudor", "Miron", 2L, 2L, 0, new ArrayList<>(List.of(101L))));
        teacherFileRepository.create(new Teacher("Bogdan", "Tarean", 10L, new ArrayList<>(List.of(100L))));
        teacherFileRepository.create(new Teacher("Alex", "Popescu", 11L, new ArrayList<>(List.of(101L))));
        courseFileRepository.getAll().clear();
        studentFileRepository.getAll().clear();
        teacherFileRepository.getAll().clear();
    }

    /**
     * check if add works properly
     */
    @Test
    void addCourse() throws IOException {
        courseFileRepository.readDataFromFile();
        assertEquals(courseController.getAllCourses().size(), 2);
        courseController.addCourse(course1);
        assertEquals(courseController.getAllCourses().get(2), course1);
        assertEquals(courseController.getAllCourses().size(), 3);
    }

    /**
     * check if getAll works properly
     */
    @Test
    void getAllCourses() throws IOException {
        courseFileRepository.readDataFromFile();
        assertEquals(courseController.getAllCourses().size(), 2);
        courseController.addCourse(course1);
        assertEquals(courseController.getAllCourses().get(2), course1);
        assertEquals(courseController.getAllCourses().size(), 3);
        courseController.addCourse(course2);
        assertEquals(courseController.getAllCourses().get(3), course2);
        assertEquals(courseController.getAllCourses().size(), 4);
    }

    /**
     * check if update works properly
     */
    @Test
    void updateCourse() throws IOException {
        courseFileRepository.readDataFromFile();
        assertEquals(courseController.getAllCourses().size(), 2);
        courseController.addCourse(course1);
        assertEquals(courseController.getAllCourses().get(2), course1);
        assertEquals(courseController.getAllCourses().size(), 3);
        courseController.updateCourse(course3);
        assertEquals(courseController.getAllCourses().get(2), course3);
        assertEquals(courseController.getAllCourses().size(), 3);
    }

    /**
     * check if delete works properly
     */
    @Test
    void deleteCourse() throws IOException {
        courseFileRepository.readDataFromFile();
        assertEquals(courseController.getAllCourses().size(), 2);
        courseController.addCourse(course1);
        assertEquals(courseController.getAllCourses().get(2), course1);
        assertEquals(courseController.getAllCourses().size(), 3);
        courseController.addCourse(course2);
        assertEquals(courseController.getAllCourses().get(3), course2);
        assertEquals(courseController.getAllCourses().size(), 4);
        courseController.deleteCourse(course2);
        assertEquals(courseController.getAllCourses().size(), 3);
    }

    /**
     * check if sort works properly
     */
    @Test
    void sortCoursesByCredits() throws IOException {
        courseFileRepository.readDataFromFile();
        assertEquals(courseController.getAllCourses().size(), 2);
        assertEquals(courseController.getAllCourses().get(0).getCourseId(), 100);
        assertEquals(courseController.getAllCourses().get(1).getCourseId(), 101);
        courseController.sortCoursesByCredits();
        assertEquals(courseController.getAllCourses().size(), 2);
        assertEquals(courseController.getAllCourses().get(0).getCourseId(), 101);
        assertEquals(courseController.getAllCourses().get(1).getCourseId(), 100);
        assertEquals(courseController.getAllCourses().size(), 2);
    }

    /**
     * check if filter works properly
     */
    @Test
    void filterCoursesByCredits() throws IOException {
        courseFileRepository.readDataFromFile();
        assertEquals(courseController.getAllCourses().size(), 2);
        assertEquals(courseController.filterCoursesByCredits(6).size(), 1);
    }
}