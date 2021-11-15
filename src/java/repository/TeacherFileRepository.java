package repository;

import model.Student;
import model.Teacher;

import java.io.IOException;
import java.util.List;

public class TeacherFileRepository extends TeacherRepository implements FileRepository<Teacher>{
    @Override
    public List<Teacher> readDataFromFile() throws IOException {
        return null;
    }

    @Override
    public void writeDataToFile() {

    }
}
