package model;

import java.util.Objects;

public class Person {

    protected String firstName;
    protected String lastName;
    protected long personId;

    public Person() {
    }

    public Person(String firstName, String lastName, long personId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }


    /**
     * print person and their details
     */
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personId=" + personId +
                '}';
    }

    /**
     * check if two persons are equal
     *
     * @param o is a person object
     * @return true if two persons are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return getPersonId() == person.getPersonId() && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName());
    }

    /**
     * @return a hashcode of person
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPersonId());
    }


}
