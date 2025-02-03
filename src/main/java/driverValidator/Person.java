package option2;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a person with a first name, last name, and optional date of birth.
 */
public class Person {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    /**
     * Constructs a person with the given first name and last name.
     *
     * @param firstName The first name of the person.
     * @param lastName  The last name of the person.
     */
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructs a person with the given first name, last name, and date of birth.
     *
     * @param firstName   The first name of the person.
     * @param lastName    The last name of the person.
     * @param dateOfBirth The date of birth of the person.
     */
    public Person(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the date of birth of the person.
     *
     * @return The date of birth of the person, or null if not set.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(dateOfBirth, person.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}