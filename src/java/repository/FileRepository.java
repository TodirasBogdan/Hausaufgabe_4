package repository;

import model.Student;

import java.io.IOException;
import java.util.List;


public interface FileRepository<T> extends ICrudRepository<T>{
    List<T> readDataFromFile() throws IOException;
    void writeDataToFile();
}
