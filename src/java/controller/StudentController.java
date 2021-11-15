package controller;

import model.Student;
import repository.StudentFileRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentController {

    private StudentFileRepository studentFileRepository;

    public StudentController() {
    }

    public StudentController(StudentFileRepository studentFileRepository) {
        this.studentFileRepository = studentFileRepository;
    }

    public StudentFileRepository getStudentFileRepository() {
        return studentFileRepository;
    }

    public void setStudentFileRepository(StudentFileRepository studentFileRepository) {
        this.studentFileRepository = studentFileRepository;
    }

    public void readDataFromStudentFile() throws IOException {
        this.studentFileRepository.readDataFromFile();
    }

    public void writeDataStudentToFile() {
        this.studentFileRepository.writeDataToFile();
    }

    public Student addStudent(Student student) {
        this.studentFileRepository.create(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return this.studentFileRepository.getAll();
    }

    public Student updateStudent(Student student) {
        return this.studentFileRepository.update(student);
    }

    public void deleteStudent(Student student) {
        this.studentFileRepository.delete(student);
    }

    /**
     * sorts all students by their name
     *
     * @return a sorted list of students
     */
    public List<Student> sortStudentsByName() {
        List<Student> sortedStudents = this.studentFileRepository.getAll();
        Comparator<Student> compareByName = Student::compareTo;
        sortedStudents.sort(compareByName);
        return sortedStudents;
    }

    /**
     * filters all students by their total credits
     *
     * @param credits is number of credits to be compared with
     * @return a filtered list of students
     */
    public List<Student> filterStudentsByTotalCredits(int credits) {
        return this.studentFileRepository.getAll().stream().filter(student -> student.getTotalCredits() >= credits).collect(Collectors.toList());
    }

}
