package repository;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public interface IFileRepository<T> extends ICrudRepository<T> {

    static List<Long> convertJsonArray(JsonNode jsonArray) {
        List<Long> convertedArray = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            convertedArray.add(jsonArray.get(i).asLong());
        }
        return convertedArray;
    }

    void readDataFromFile() throws IOException;

    void writeDataToFile() throws IOException;
}
