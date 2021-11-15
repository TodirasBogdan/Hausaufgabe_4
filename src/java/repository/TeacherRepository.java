package repository;

import model.Teacher;

import java.io.IOException;
import java.util.Objects;

public class TeacherRepository extends InMemoryRepository<Teacher> {

    public TeacherRepository() {
        super();
    }

    /**
     * implement update from InMemoryRepository for TeacherRepository
     *
     * @param obj is a new Teacher object
     * @return obj if obj is not in TeacherRepository, otherwise update teacher and return null
     */
    @Override
    public Teacher update(Teacher obj) throws IOException {
        for (Teacher teacher : repoList)
            if (Objects.equals(teacher.getPersonId(), obj.getPersonId())) {
                repoList.remove(teacher);
                repoList.add(obj);
                return null;
            }
        return obj;
    }
}
