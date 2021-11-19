package main;

import controller.CourseController;
import controller.StudentController;
import controller.TeacherController;
import exceptions.NullValueException;
import repository.CourseFileRepository;
import repository.StudentFileRepository;
import repository.TeacherFileRepository;
import view.Ui;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, NullValueException {

        CourseFileRepository courseFileRepository = new CourseFileRepository("courses.json");
        StudentFileRepository studentFileRepository = new StudentFileRepository("students.json");
        TeacherFileRepository teacherFileRepository = new TeacherFileRepository("teachers.json");

        courseFileRepository.readDataFromFile();
        studentFileRepository.readDataFromFile();
        teacherFileRepository.readDataFromFile();

        CourseController courseController = new CourseController(courseFileRepository, studentFileRepository, teacherFileRepository);
        StudentController studentController = new StudentController(courseFileRepository, studentFileRepository);
        TeacherController teacherController = new TeacherController(courseFileRepository, teacherFileRepository);

        Ui ui = new Ui(courseController, studentController, teacherController);

        ui.display();
    }
}
