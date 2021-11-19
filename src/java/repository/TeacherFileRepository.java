package repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import model.Teacher;

import java.io.*;
import java.util.ArrayList;

public class TeacherFileRepository extends TeacherRepository implements IFileRepository<Teacher> {

    private String teacherFile;

    public TeacherFileRepository(String teacherFile) {
        this.teacherFile = teacherFile;
    }

    public String getTeacherFile() {
        return teacherFile;
    }

    public void setTeacherFile(String teacherFile) {
        this.teacherFile = teacherFile;
    }

    @Override
    public void readDataFromFile() throws IOException {
        Reader reader = new BufferedReader(new FileReader(this.teacherFile));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        for (JsonNode jsonNode : parser) {
            Teacher teacher = new Teacher();

            teacher.setFirstName(jsonNode.path("firstName").asText());
            teacher.setLastName(jsonNode.path("lastName").asText());
            teacher.setPersonId(jsonNode.path("personId").asLong());

            JsonNode jsonArray = jsonNode.get("coursesIds");
            if (jsonArray.size() > 0) {
                teacher.setCoursesIds(IFileRepository.convertJsonArray(jsonArray));
            } else {
                teacher.setCoursesIds(new ArrayList<>());
            }
            this.create(teacher);
        }
        reader.close();
    }

    @Override
    public void writeDataToFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(new File(this.teacherFile), repoList);
    }

    /**
     * creates a teacher and updates file
     */
    @Override
    public Teacher create(Teacher obj) throws IOException {
        super.create(obj);
        writeDataToFile();
        return obj;
    }

    /**
     * updates teacher and updates file
     *
     * @param obj is a new Teacher object
     */
    @Override
    public Teacher update(Teacher obj) throws IOException {
        super.update(obj);
        writeDataToFile();
        return super.update(obj);
    }

    /**
     * deletes teacher and updates file
     */
    @Override
    public void delete(Teacher obj) throws IOException {
        super.delete(obj);
        writeDataToFile();
    }


}
