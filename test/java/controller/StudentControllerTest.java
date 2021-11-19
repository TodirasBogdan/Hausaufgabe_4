package controller;

import model.Course;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CourseFileRepository;
import repository.StudentFileRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentControllerTest {

    CourseFileRepository courseFileRepository = new CourseFileRepository("./test/resources/testCourses.json");
    StudentFileRepository studentFileRepository = new StudentFileRepository("./test/resources/testStudents.json");

    StudentController studentController = new StudentController(courseFileRepository, studentFileRepository);

    /**
     * sets the testStudents file
     */
    @BeforeEach
    public void setStudentFile() throws IOException {
        courseFileRepository.getAll().clear();
        studentFileRepository.getAll().clear();
        courseFileRepository.create(new Course("Geometrie", 10L, 20, new ArrayList<>(Arrays.asList(1L, 2L)), 6, 100L));
        courseFileRepository.create(new Course("Algebra", 11L, 25, new ArrayList<>(List.of(2L)), 5, 101L));
        studentFileRepository.create(new Student("Vlad", "Nechifor", 1L, 1L, 0, new ArrayList<>(Arrays.asList(100L, 101L))));
        studentFileRepository.create(new Student("Tudor", "Miron", 2L, 2L, 5, new ArrayList<>(List.of(101L))));
        courseFileRepository.getAll().clear();
        studentFileRepository.getAll().clear();
    }

    @Test
    void sortStudentsByName() throws IOException {
        studentFileRepository.readDataFromFile();
        assertEquals(studentController.getAllStudents().size(), 2);
        assertEquals(studentController.getAllStudents().get(0).getStudentId(), 1L);
        assertEquals(studentController.getAllStudents().get(1).getStudentId(), 2L);
        studentController.sortStudentsByName();
        assertEquals(studentController.getAllStudents().size(), 2);
        assertEquals(studentController.getAllStudents().get(0).getStudentId(), 2L);
        assertEquals(studentController.getAllStudents().get(1).getStudentId(), 1L);
        assertEquals(studentController.getAllStudents().size(), 2);
    }

    @Test
    void filterStudentsByTotalCredits() throws IOException {
        studentFileRepository.readDataFromFile();
        assertEquals(studentController.getAllStudents().size(), 2);
        assertEquals(studentController.filterStudentsByTotalCredits(3).size(), 1);
    }
}