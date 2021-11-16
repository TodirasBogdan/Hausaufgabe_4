package controller;

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

    public void readDataFromTeacherFile() throws IOException {
        this.teacherFileRepository.readDataFromFile();
    }

    public void writeDataToTeacherFile() throws IOException {
        this.teacherFileRepository.writeDataToFile();
    }

    public Teacher addTeacher(Teacher teacher) throws IOException {
        return this.teacherFileRepository.create(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return this.teacherFileRepository.getAll();
    }

    public Teacher updateTeacher(Teacher teacher) throws IOException {
        return this.teacherFileRepository.update(teacher);
    }

    public void deleteTeacher(Teacher teacher) throws IOException {
        this.teacherFileRepository.delete(teacher);
    }

}
