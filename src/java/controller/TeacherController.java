package controller;

import model.Course;
import model.Teacher;
import repository.CourseFileRepository;
import repository.TeacherFileRepository;

import java.io.IOException;
import java.util.List;

public class TeacherController {

    private CourseFileRepository courseFileRepository;
    private TeacherFileRepository teacherFileRepository;

    public TeacherController(CourseFileRepository courseFileRepository, TeacherFileRepository teacherFileRepository) {
        this.courseFileRepository = courseFileRepository;
        this.teacherFileRepository = teacherFileRepository;
    }

    public CourseFileRepository getCourseFileRepository() {
        return courseFileRepository;
    }

    public void setCourseFileRepository(CourseFileRepository courseFileRepository) {
        this.courseFileRepository = courseFileRepository;
    }

    public TeacherFileRepository getTeacherFileRepository() {
        return teacherFileRepository;
    }

    public void setTeacherFileRepository(TeacherFileRepository teacherFileRepository) {
        this.teacherFileRepository = teacherFileRepository;
    }

    /**
     * adds a teacher and updates all files
     */
    public Teacher addTeacher(Teacher teacher) throws IOException {
        List<Long> coursesIds = teacher.getCoursesIds();
        boolean exists;

        if (coursesIds.size() == 0) {
            this.teacherFileRepository.create(teacher);
            this.teacherFileRepository.writeDataToFile();
            return teacher;
        }

        List<Course> courses = this.courseFileRepository.getAll();
        for (Long courseId : coursesIds) {
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

        for (Long courseId : coursesIds) {
            for (Course course : courses) {
                if (course.getCourseId() == courseId) {
                    course.setTeacherId(teacher.getPersonId());
                }
            }
        }
        this.teacherFileRepository.create(teacher);
        this.courseFileRepository.writeDataToFile();
        this.teacherFileRepository.writeDataToFile();
        return teacher;
    }

    /**
     * returns a list of all teachers
     */
    public List<Teacher> getAllTeachers() {
        return this.teacherFileRepository.getAll();
    }

    /**
     * updates a teacher and updates all files
     */
    public Teacher updateTeacher(Teacher teacher) throws IOException {
        this.teacherFileRepository.update(teacher);
        this.teacherFileRepository.writeDataToFile();
        return teacher;
    }

    /**
     * deletes a teacher and updates all files
     */
    public void deleteTeacher(Teacher teacher) throws IOException {
        Long teacherId = teacher.getPersonId();
        List<Course> courses = this.courseFileRepository.getAll();
        for (Course course : courses) {
            if (teacherId.equals(course.getTeacherId())) {
                course.setTeacherId(null);
            }
        }
        this.teacherFileRepository.delete(teacher);
        this.teacherFileRepository.writeDataToFile();
        this.courseFileRepository.writeDataToFile();
    }

}
