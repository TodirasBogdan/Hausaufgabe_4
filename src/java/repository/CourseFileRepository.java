package repository;

import model.Course;
import model.Student;

import java.io.IOException;
import java.util.List;

public class CourseFileRepository extends CourseRepository implements FileRepository<Course>{

    @Override
    public List<Course> readDataFromFile() throws IOException {
        return null;
    }

    @Override
    public void writeDataToFile() {

    }
}
