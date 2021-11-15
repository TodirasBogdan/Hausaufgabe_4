package repository;

import model.Course;

public class CourseRepository extends InMemoryRepository<Course> {

    public CourseRepository() {
        super();
    }

    /**
     * implement update from InMemoryRepository for CourseRepository
     *
     * @param obj is a new Course object
     * @return obj if obj is not in CourseRepository, otherwise update course and return null
     */
    @Override
    public Course update(Course obj) {
        for (Course course : repoList)
            if (course.getCourseId() == obj.getCourseId()) {
                repoList.remove(course);
                repoList.add(obj);
                return null;
            }
        return obj;
    }
}
