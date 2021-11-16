package model;

import java.util.List;
import java.util.Objects;

public class Course {

    private String name;
    private Long teacherId;
    private int maxEnrollment;
    private List<Long> studentsEnrolledIds;
    private int credits;
    private long courseId;

    public Course() {
    }

    public Course(String name, Long teacherId, int maxEnrollment, List<Long> studentsEnrolledIds, int credits, long courseId) {
        this.name = name;
        this.teacherId = teacherId;
        this.maxEnrollment = maxEnrollment;
        this.studentsEnrolledIds = studentsEnrolledIds;
        this.credits = credits;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public List<Long> getStudentsEnrolledIds() {
        return studentsEnrolledIds;
    }

    public void setStudentsEnrolledIds(List<Long> studentsEnrolledIds) {
        this.studentsEnrolledIds = studentsEnrolledIds;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }


    /**
     * print course and its details
     */
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", maxEnrollment=" + maxEnrollment +
                ", credits=" + credits +
                '}';
    }

    /**
     * check if two courses are equal
     *
     * @param o is a course object
     * @return true if two courses are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return getMaxEnrollment() == course.getMaxEnrollment() && getCredits() == course.getCredits() && getCourseId() == course.getCourseId() && Objects.equals(getName(), course.getName()) && Objects.equals(getTeacherId(), course.getTeacherId()) && Objects.equals(getStudentsEnrolledIds(), course.getStudentsEnrolledIds());
    }


    /**
     * compares 2 courses by their credits
     *
     * @param other is the course to be compared with
     * @return an integer value >0, <0 or 0 if this course has a higher, lower or equal number of credits with other
     */
    public int compareTo(Course other) {
        return Integer.compare(this.getCredits(), other.getCredits());
    }


    /**
     * @return a hashcode of course
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTeacherId(), getMaxEnrollment(), getStudentsEnrolledIds(), getCredits(), getCourseId());
    }
}
