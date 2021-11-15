package repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Student;

import java.io.*;
import java.util.ArrayList;

public class StudentFileRepository extends StudentRepository implements IFileRepository<Student> {

    private String studentFile;

    public StudentFileRepository(String studentFile) {
        this.studentFile = studentFile;
    }

    public String getStudentFile() {
        return studentFile;
    }

    public void setStudentFile(String studentFile) {
        this.studentFile = studentFile;
    }

    @Override
    public void readDataFromFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader(this.studentFile));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode jsonNode : parser) {
            Student student = new Student();

            student.setFirstName(jsonNode.path("firstName").asText());
            student.setLastName(jsonNode.path("lastName").asText());
            student.setPersonId(jsonNode.path("personId").asLong());
            student.setStudentId(jsonNode.path("studentId").asLong());
            student.setTotalCredits(jsonNode.path("totalCredits").asInt());

            JsonNode jsonArray = jsonNode.get("enrolledCoursesIds");
            if (jsonArray.size() > 0) {
                student.setEnrolledCoursesIds(IFileRepository.convertJsonArray(jsonArray));
            } else {
                student.setEnrolledCoursesIds(new ArrayList<>());
            }

            this.create(student);

        }

        reader.close();
    }

    @Override
    public void writeDataToFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(new File(this.studentFile), repoList);
    }

    @Override
    public Student create(Student obj) throws IOException {
        super.create(obj);
        writeDataToFile();
        return super.create(obj);
    }

    @Override
    public Student update(Student obj) throws IOException {
        super.update(obj);
        writeDataToFile();
        return super.update(obj);
    }

    @Override
    public void delete(Student obj) throws IOException {
        super.delete(obj);
        writeDataToFile();
    }


}
