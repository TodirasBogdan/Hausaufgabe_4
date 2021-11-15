package controller;

import model.Teacher;
import repository.TeacherFileRepository;

import java.io.IOException;
import java.util.List;

public class TeacherController {

    private TeacherFileRepository teacherFileRepository;

    public TeacherController() {
    }

    public TeacherController(TeacherFileRepository teacherFileRepository) {
        this.teacherFileRepository = teacherFileRepository;
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

    public void writeDataToTeacherFile() {
        this.teacherFileRepository.writeDataToFile();
    }

    public Teacher addTeacher(Teacher teacher) {
        this.teacherFileRepository.create(teacher);
        return teacher;
    }

    public List<Teacher> getAllTeachers() {
        return this.teacherFileRepository.getAll();
    }

    public Teacher updateTeacher(Teacher teacher) {
        return this.teacherFileRepository.update(teacher);
    }

    public void deleteTeacher(Teacher teacher) {
        this.teacherFileRepository.delete(teacher);
    }

}
