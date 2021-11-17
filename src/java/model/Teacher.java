package model;

import java.util.List;
import java.util.Objects;

public class Teacher extends Person {

    private List<Long> coursesIds;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, long personId, List<Long> courses) {
        super(firstName, lastName, personId);
        this.coursesIds = courses;
    }



    public List<Long> getCoursesIds() {
        return coursesIds;
    }

    public void setCoursesIds(List<Long> coursesIds) {
        this.coursesIds = coursesIds;
    }


    /**
     * print teacher and their details
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "firstName=" + super.getFirstName() +
                ", lastName=" + super.getLastName() +
                '}';
    }

    /**
     * check if two teachers are equal
     *
     * @param o is a teacher object
     * @return true if two teachers are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher teacher)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCoursesIds(), teacher.getCoursesIds());
    }

    /**
     * @return a hashcode of teacher
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCoursesIds());
    }
}
