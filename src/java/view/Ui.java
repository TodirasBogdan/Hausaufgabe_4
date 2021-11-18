package view;


import controller.CourseController;
import controller.StudentController;
import controller.TeacherController;
import model.Course;
import model.Student;
import model.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private CourseController courseController;
    private StudentController studentController;
    private TeacherController teacherController;

    public Ui(CourseController courseController, StudentController studentController, TeacherController teacherController) {
        this.courseController = courseController;
        this.studentController = studentController;
        this.teacherController = teacherController;
    }

    public CourseController getCourseController() {
        return courseController;
    }

    public void setCourseController(CourseController courseController) {
        this.courseController = courseController;
    }

    public StudentController getStudentController() {
        return studentController;
    }

    public void setStudentController(StudentController studentController) {
        this.studentController = studentController;
    }

    public TeacherController getTeacherController() {
        return teacherController;
    }

    public void setTeacherController(TeacherController teacherController) {
        this.teacherController = teacherController;
    }


    /**
     * adds a course
     */
    public void addCourse() throws IOException {
        System.out.println("Add course");
        Scanner scanner = new Scanner(System.in);
        System.out.println("name: ");
        String name = scanner.next();
        System.out.println("courseId: ");
        Long courseId = scanner.nextLong();
        System.out.println("teacherId: ");
        Long teacherId = scanner.nextLong();
        System.out.println("maxEnrollment: ");
        int maxEnrollment = scanner.nextInt();
        System.out.println("number of courses: ");
        int number = scanner.nextInt();
        List<Long> studentsEnrolled = new ArrayList<>();
        List<Student> students = this.studentController.getAllStudents();
        for (int i = 0; i < number; i++) {
            System.out.println("enrolled student Id: ");
            long studentId = scanner.nextLong();
            studentsEnrolled.add(studentId);
            for (Student student : students) {
                if (student.getStudentId() == studentId) {
                    student.getEnrolledCoursesIds().add(courseId);
                }
            }
        }
        System.out.println("credits: ");
        int credits = scanner.nextInt();

        Course course = new Course(name, teacherId, maxEnrollment, studentsEnrolled, credits, courseId);
        courseController.addCourse(course);
    }

    /**
     * prints all courses
     */
    public void getAllCourses() {
        System.out.println("Get all courses");
        List<Course> courses = courseController.getAllCourses();
        for (Course course : courses) {
            System.out.println(course.toString());
        }
    }

    /**
     * updates a course
     */
    public void updateCourse() throws IOException {
        System.out.println("Update course");
        Scanner scanner = new Scanner(System.in);
        System.out.println("name: ");
        String name = scanner.next();
        System.out.println("courseId: ");
        long courseId = scanner.nextLong();
        System.out.println("teacherId: ");
        Long teacherId = scanner.nextLong();
        System.out.println("maxEnrollment: ");
        int maxEnrollment = scanner.nextInt();
        System.out.println("number of courses: ");
        int number = scanner.nextInt();
        List<Long> studentsEnrolled = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            System.out.println("studentEnrolled: ");
            Long studentId = scanner.nextLong();
            studentsEnrolled.add(studentId);
        }
        System.out.println("credits: ");
        int credits = scanner.nextInt();

        Course course = new Course(name, teacherId, maxEnrollment, studentsEnrolled, credits, courseId);
        courseController.updateCourse(course);
    }

    /**
     * deletes a course
     */
    public void deleteCourse() throws IOException {
        System.out.println("Delete course");
        Scanner scanner = new Scanner(System.in);
        System.out.println("courseId: ");
        Long courseId = scanner.nextLong();
        List<Course> courses = courseController.getAllCourses();
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                courseController.deleteCourse(course);
                break;
            }
        }
        List<Teacher> teachers = teacherController.getAllTeachers();
        for (Teacher teacher : teachers) {
            List<Long> teacherCourses = teacher.getCoursesIds();
            teacherCourses.remove(courseId);
        }
        List<Student> students = studentController.getAllStudents();
        for (Student student : students) {
            List<Long> studentCourses = student.getEnrolledCoursesIds();
            studentCourses.remove(courseId);
        }
    }

    /**
     * sorts all courses
     */
    public void sortCoursesByCredits() {
        System.out.println("Sorted courses: ");
        List<Course> sortedCourses = courseController.sortCoursesByCredits();
        for (Course course : sortedCourses) {
            System.out.println(course.toString());
        }
    }

    /**
     * filters all courses
     */
    public void filterCoursesByCredits() {
        System.out.println("Filtered courses: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("credits: ");
        int credits = scanner.nextInt();
        List<Course> filteredCourses = courseController.filterCoursesByCredits(credits);
        for (Course course : filteredCourses) {
            System.out.println(course.toString());
        }
    }


    /**
     * adds a student
     */
    public void addStudent() throws IOException {
        System.out.println("Add Student");
        Scanner scanner = new Scanner(System.in);
        System.out.println("firstName: ");
        String firstName = scanner.next();
        System.out.println("lastName: ");
        String lastName = scanner.next();
        System.out.println("personId: ");
        long personId = scanner.nextLong();
        System.out.println("studentId: ");
        Long studentId = scanner.nextLong();
        System.out.println("totalCredits: ");
        int totalCredits = scanner.nextInt();
        System.out.println("number of enrolled courses: ");
        int number = scanner.nextInt();
        List<Long> enrolledCoursesIds = new ArrayList<>();
        List<Course> courses = this.courseController.getAllCourses();
        for (int i = 0; i < number; i++) {
            System.out.println("enrolled course Id: ");
            long courseId = scanner.nextLong();
            enrolledCoursesIds.add(courseId);
            for (Course course : courses) {
                if (course.getCourseId() == courseId) {
                    course.getStudentsEnrolledIds().add(studentId);
                }
            }
        }

        Student student = new Student(firstName, lastName, personId, studentId, totalCredits, enrolledCoursesIds);
        studentController.addStudent(student);
    }

    /**
     * prints all students
     */
    public void getAllStudents() {
        System.out.println("Get all students: ");
        List<Student> students = studentController.getAllStudents();
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    /**
     * updates a student
     */
    public void updateStudent() throws IOException {
        System.out.println("Update Student");
        Scanner scanner = new Scanner(System.in);
        System.out.println("firstName: ");
        String firstName = scanner.next();
        System.out.println("lastName: ");
        String lastName = scanner.next();
        System.out.println("personId: ");
        long personId = scanner.nextLong();
        System.out.println("studentId: ");
        Long studentId = scanner.nextLong();
        System.out.println("totalCredits: ");
        int totalCredits = scanner.nextInt();
        System.out.println("number of enrolled courses: ");
        int number = scanner.nextInt();
        List<Long> enrolledCoursesIds = new ArrayList<>();
        List<Course> courses = this.courseController.getAllCourses();
        for (int i = 0; i < number; i++) {
            System.out.println("enrolled course Id: ");
            long courseId = scanner.nextLong();
            enrolledCoursesIds.add(courseId);
            for (Course course : courses) {
                if (course.getCourseId() == courseId) {
                    course.getStudentsEnrolledIds().add(studentId);
                }
            }
        }

        Student student = new Student(firstName, lastName, personId, studentId, totalCredits, enrolledCoursesIds);
        studentController.updateStudent(student);
    }

    /**
     * deletes a student
     */
    public void deleteStudent() throws IOException {
        System.out.println("Delete student");
        Scanner scanner = new Scanner(System.in);
        System.out.println("studentId: ");
        long studentId = scanner.nextLong();
        List<Student> students = studentController.getAllStudents();
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                studentController.deleteStudent(student);
                break;
            }
        }
        List<Course> courses = courseController.getAllCourses();
        for (Course course : courses) {
            List<Long> courseStudentsEnrolledIds = course.getStudentsEnrolledIds();
            courseStudentsEnrolledIds.remove(studentId);
        }
    }

    /**
     * sorts all students
     */
    public void sortStudentsByName() {
        System.out.println("Sorted students: ");
        List<Student> sortedStudents = studentController.sortStudentsByName();
        for (Student student : sortedStudents) {
            System.out.println(student.toString());
        }
    }

    /**
     * filters all students
     */
    public void filterStudentsByTotalCredits() {
        System.out.println("Filtered students: ");
        Scanner scanner = new Scanner(System.in);
        int totalCredits = scanner.nextInt();
        List<Student> filteredStudents = studentController.filterStudentsByTotalCredits(totalCredits);
        for (Student student : filteredStudents) {
            System.out.println(student.toString());
        }
    }


    /**
     * adds a teacher
     */
    public void addTeacher() throws IOException {
        System.out.println("Add Teacher");
        Scanner scanner = new Scanner(System.in);
        System.out.println("firstName: ");
        String firstName = scanner.next();
        System.out.println("lastName: ");
        String lastName = scanner.next();
        System.out.println("personId: ");
        Long personId = scanner.nextLong();
        System.out.println("number of courses: ");
        int number = scanner.nextInt();
        List<Long> coursesIds = new ArrayList<>();
        List<Course> courses = this.courseController.getAllCourses();
        for (int i = 0; i < number; i++) {
            System.out.println("course Id: ");
            long courseId = scanner.nextLong();
            coursesIds.add(courseId);
            for (Course course : courses) {
                if (course.getCourseId() == courseId) {
                    course.setTeacherId(personId);
                }
            }
        }
        Teacher teacher = new Teacher(firstName, lastName, personId, coursesIds);
        teacherController.addTeacher(teacher);
    }

    /**
     * prints all teachers
     */
    public void getAllTeachers() {
        System.out.println("Get all teachers ");
        List<Teacher> teachers = teacherController.getAllTeachers();
        for (Teacher teacher : teachers) {
            System.out.println(teacher.toString());
        }
    }

    /**
     * updates a teacher
     */
    public void updateTeacher() throws IOException {
        System.out.println("Update Teacher");
        Scanner scanner = new Scanner(System.in);
        System.out.println("firstName: ");
        String firstName = scanner.next();
        System.out.println("lastName: ");
        String lastName = scanner.next();
        System.out.println("personId: ");
        Long personId = scanner.nextLong();
        System.out.println("number of courses: ");
        int number = scanner.nextInt();
        List<Long> coursesIds = new ArrayList<>();
        List<Course> courses = this.courseController.getAllCourses();
        for (int i = 0; i < number; i++) {
            System.out.println("course Id: ");
            long courseId = scanner.nextLong();
            coursesIds.add(courseId);
            for (Course course : courses) {
                if (course.getCourseId() == courseId) {
                    course.setTeacherId(personId);
                }
            }
        }
        Teacher teacher = new Teacher(firstName, lastName, personId, coursesIds);
        teacherController.updateTeacher(teacher);
    }

    /**
     * deletes s teacher
     */
    public void deleteTeacher() throws IOException {
        System.out.println("Delete teacher ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("teacherId: ");
        long teacherId = scanner.nextLong();
        List<Teacher> teachers = teacherController.getAllTeachers();
        for (Teacher teacher : teachers) {
            if (teacher.getPersonId() == teacherId) {
                teacherController.deleteTeacher(teacher);
                break;
            }
        }
    }

}
