package repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Course;

import java.io.*;
import java.util.ArrayList;

public class CourseFileRepository extends CourseRepository implements IFileRepository<Course> {

    private String courseFile;

    public CourseFileRepository(String courseFile) {
        this.courseFile = courseFile;
    }

    public String getCourseFile() {
        return courseFile;
    }

    public void setCourseFile(String courseFile) {
        this.courseFile = courseFile;
    }

    @Override
    public void readDataFromFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader(this.courseFile));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode jsonNode : parser) {
            Course course = new Course();

            course.setName(jsonNode.path("name").asText());
            course.setTeacher(jsonNode.path("teacher").asText());
            course.setMaxEnrollment(jsonNode.path("maxEnrollment").asInt());
            course.setCredits(jsonNode.path("credits").asInt());
            course.setCourseId(jsonNode.path("courseId").asLong());

            JsonNode jsonArray = jsonNode.get("studentsEnrolledIds");
            if (jsonArray.size() > 0) {
                course.setStudentsEnrolledIds(IFileRepository.convertJsonArray(jsonArray));
            } else {
                course.setStudentsEnrolledIds(new ArrayList<>());
            }

            this.create(course);
        }

        reader.close();
    }

    @Override
    public void writeDataToFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(new File(this.courseFile), repoList);
    }

    @Override
    public Course create(Course obj) throws IOException {
        super.create(obj);
        writeDataToFile();
        return super.create(obj);
    }

    @Override
    public Course update(Course obj) throws IOException {
        super.update(obj);
        writeDataToFile();
        return super.update(obj);
    }

    @Override
    public void delete(Course obj) throws IOException {
        super.delete(obj);
        writeDataToFile();
    }
}
