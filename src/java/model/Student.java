package model;

import java.util.List;
import java.util.Objects;

public class Student extends Person {

    private long studentId;
    private int totalCredits;
    private List<Long> enrolledCoursesIds;

    public Student() {
    }

    public Student(String firstName, String lastName, long personId, long studentId, int totalCredits, List<Long> enrolledCoursesIds) {
        super(firstName, lastName, personId);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCoursesIds = enrolledCoursesIds;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Long> getEnrolledCourses() {
        return enrolledCoursesIds;
    }

    public void setEnrolledCourses(List<Long> enrolledCoursesIds) {
        this.enrolledCoursesIds = enrolledCoursesIds;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    /**
     * print student and their details
     */
    @Override
    public String toString() {
        return "Student{" +
                "firstName=" + super.getFirstName() +
                ", lastName=" + super.getLastName() +
                ", studentId=" + studentId +
                ", totalCredits=" + totalCredits +
                '}';
    }

    /**
     * check if two students are equal
     *
     * @param o is a student object
     * @return true if two students are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        if (!super.equals(o)) return false;
        return getTotalCredits() == student.getTotalCredits() && Objects.equals(getEnrolledCourses(), student.getEnrolledCourses());
    }

    /**
     * compares 2 students by their full name
     * @param other is the student to be compared with
     * @return an integer value >0, <0 or 0 if this student has a higher, lower or equal lexicographical value compared with other
     */
    public int compareTo(Student other) {
        String thisName = super.getFirstName() + " " + super.getLastName();
        String otherName = other.getFirstName() + " " + other.getLastName();
        return thisName.compareTo(otherName);
    }

    /**
     * @return a hashcode of student
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTotalCredits(), getEnrolledCourses());
    }
}
