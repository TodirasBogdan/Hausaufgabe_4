package controllerTest;

import controller.CourseController;
import model.Course;
import model.Teacher;
import repository.CourseFileRepository;
import repository.StudentFileRepository;
import repository.TeacherFileRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerTest {

    CourseFileRepository courseFileRepository = new CourseFileRepository(Objects.requireNonNull(getClass().getClassLoader().getResource("testCourses.json")).getFile());
    StudentFileRepository studentFileRepository = new StudentFileRepository(Objects.requireNonNull(getClass().getClassLoader().getResource("testStudents.json")).getFile());
    TeacherFileRepository teacherFileRepository = new TeacherFileRepository(Objects.requireNonNull(getClass().getClassLoader().getResource("testTeachers.json")).getFile());

    CourseController courseController = new CourseController(courseFileRepository, studentFileRepository, teacherFileRepository);

    List<Long> coursesIds = new ArrayList<>();
    private final Teacher teacher = new Teacher("Ion", "Ionescu", 789, coursesIds);
    List<Course> courses = new ArrayList<>();
    List<Long> studentsIds = new ArrayList<>();
    private final Course course1 = new Course("Map", teacher.getPersonId(), 30, studentsIds, 5, 123456);
    private final Course course2 = new Course("Fp", teacher.getPersonId(), 30, studentsIds, 7, 654321);
    private final Course course3 = new Course("Map", teacher.getPersonId(), 30, studentsIds, 6, 123456);


    public void testCreate(){
        assert(courseController.getAllCourses().size()==0);
    }


}
