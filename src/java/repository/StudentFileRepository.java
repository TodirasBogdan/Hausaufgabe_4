package repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Course;
import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentFileRepository extends StudentRepository implements FileRepository<Student> {

    @Override
    public List<Student> readDataFromFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader("data.json"));

        List<Student> students = new LinkedList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for(JsonNode jsonNode: parser){
            Student student = new Student();

            student.setFirstName(jsonNode.path("firstName").asText());
            student.setLastName(jsonNode.path("lastName").asText());
            student.setPersonId(jsonNode.path("personId").asLong());
            student.setStudentId(jsonNode.path("studentId").asLong());
            student.setTotalCredits(jsonNode.path("totalCredits").asInt());
            //student.setEnrolledCourses(jsonNode.path("enrolledCourses"));


        }
        return students;
    }

    @Override
    public void writeDataToFile() {

    }

    @Override
    public Student update(Student obj) {
        super.update(obj);
        writeDataToFile();
        return null;
    }
}
