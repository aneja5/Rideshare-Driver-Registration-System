/**
 * This class represents a driver's violation history entry.
 * It stores the date of the violation and the type of violation.
 */
package option2;

import java.time.LocalDate;
import java.util.Objects;

public class DriverHistory {
    private LocalDate date;
    private Enum<?> violationType;

    /**
     * Constructor to create a DriverHistory instance.
     *
     * @param date The date of the violation.
     * @param violationType The type of violation as an Enum.
     */
    public DriverHistory(LocalDate date, Enum<?> violationType) {
        this.date = date;
        this.violationType = violationType;
    }

    /**
     * Gets the date of the violation.
     *
     * @return The date of the violation as a LocalDate object.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the type of violation.
     *
     * @return The type of violation as an Enum object.
     */
    public Enum<?> getViolationType() {
        return violationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverHistory that = (DriverHistory) o;
        return Objects.equals(date, that.date) && Objects.equals(violationType, that.violationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, violationType);
    }

    @Override
    public String toString() {
        return violationType.toString();
    }
}
