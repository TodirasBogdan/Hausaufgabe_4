package controller;

import model.Course;
import model.Student;
import repository.CourseFileRepository;
import repository.StudentFileRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentController {

    private CourseFileRepository courseFileRepository;
    private StudentFileRepository studentFileRepository;


    public StudentController(CourseFileRepository courseFileRepository, StudentFileRepository studentFileRepository) {
        this.courseFileRepository = courseFileRepository;
        this.studentFileRepository = studentFileRepository;
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


    public Student addStudent(Student student) throws IOException {
        List<Long> enrolledCoursesIds = student.getEnrolledCoursesIds();
        boolean exists;

        if (enrolledCoursesIds.size() == 0) {
            this.studentFileRepository.create(student);
            this.studentFileRepository.writeDataToFile();
            return student;
        }

        List<Course> courses = this.courseFileRepository.getAll();
        for (Long courseId : enrolledCoursesIds) {
            exists = false;
            for (Course course : courses) {
                if (course.getCourseId() == courseId) {
                    exists = true;
                    break;
                }
            }
            if (!exists)
                return null;
        }

        for (Long courseId : enrolledCoursesIds){
            for (Course course : courses){
                if (course.getCourseId() == courseId){
                    course.getStudentsEnrolledIds().add(student.getStudentId());
                }
            }
        }
        this.studentFileRepository.create(student);
        this.studentFileRepository.writeDataToFile();
        this.courseFileRepository.writeDataToFile();
        return student;
    }

    public List<Student> getAllStudents() {
        return this.studentFileRepository.getAll();
    }

    public Student updateStudent(Student student) throws IOException {
        this.studentFileRepository.update(student);
        this.studentFileRepository.writeDataToFile();
        return student;
    }

    public void deleteStudent(Student student) throws IOException {
        Long studentId = student.getStudentId();
        List<Course> courses = this.courseFileRepository.getAll();
        for (Course course : courses) {
            List<Long> studentsEnrolledIds = course.getStudentsEnrolledIds();
            studentsEnrolledIds.remove(studentId);
            course.setStudentsEnrolledIds(studentsEnrolledIds);
        }
        this.studentFileRepository.delete(student);
        this.studentFileRepository.writeDataToFile();
        this.courseFileRepository.writeDataToFile();
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
