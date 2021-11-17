package view;


import controller.CourseController;
import controller.StudentController;
import controller.TeacherController;
import model.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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


    public void addCourse() throws IOException {
        System.out.println("Add course");
        Scanner scanner = new Scanner(System.in);
        System.out.println("name: ");
        String name = scanner.next();
        System.out.println("teacherId: ");
        Long teacherId = scanner.nextLong();
        System.out.println("maxEnrollment: ");
        int maxEnrollment = scanner.nextInt();
        System.out.println("number of courses: ");
        int number = scanner.nextInt();
        List<Long> studentsEnrolled = new ArrayList<>();
        for(int i=0;i<number;i++){
            System.out.println("studentEnrolled: ");
            Long studentId = scanner.nextLong();
            studentsEnrolled.add(studentId);
        }
        System.out.println("credits: ");
        int credits = scanner.nextInt();
        System.out.println("courseId: ");
        Long courseId = scanner.nextLong();

        Course course = new Course(name,teacherId,maxEnrollment,studentsEnrolled,credits,courseId);
        courseController.addCourse(course);
    }

    public void getAllCourses(){
        System.out.println("Get all courses");
        List<Course> courses = courseController.getAllCourses();
        for(Course course:courses){
            System.out.println(course.toString());
        }
    }

    public void updateCourse() throws IOException {
        System.out.println("Update course");
        Scanner scanner = new Scanner(System.in);
        System.out.println("name: ");
        String name = scanner.next();
        System.out.println("teacherId: ");
        Long teacherId = scanner.nextLong();
        System.out.println("maxEnrollment: ");
        int maxEnrollment = scanner.nextInt();
        System.out.println("number of courses: ");
        int number = scanner.nextInt();
        List<Long> studentsEnrolled = new ArrayList<>();
        for(int i=0;i<number;i++){
            System.out.println("studentEnrolled: ");
            Long studentId = scanner.nextLong();
            studentsEnrolled.add(studentId);
        }
        System.out.println("credits: ");
        int credits = scanner.nextInt();
        System.out.println("courseId: ");
        Long courseId = scanner.nextLong();

        Course course = new Course(name,teacherId,maxEnrollment,studentsEnrolled,credits,courseId);
        courseController.updateCourse(course);
    }

    public void deleteCourse() throws IOException {
        System.out.println("Delete course");
        Scanner scanner = new Scanner(System.in);
        System.out.println("courseId: ");
        Long courseId = scanner.nextLong();
        List<Course> courses = courseController.getAllCourses();
        for(Course course:courses){
            if(course.getCourseId()==courseId){
                courseController.deleteCourse(course);
                break;
            }
        }
    }

    public void sortCoursesByCredits() {
        System.out.println("Sorted courses: ");
        List<Course> sortedCourses = courseController.sortCoursesByCredits();
        for(Course course:sortedCourses){
            System.out.println(course.toString());
        }
    }

    public void filterCoursesByCredits(){
        System.out.println("Filtered courses: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("credits: ");
        int credits = scanner.nextInt();
        List<Course> filteredCourses = courseController.filterCoursesByCredits(credits);
        for(Course course:filteredCourses){
            System.out.println(course.toString());
        }
    }



}
