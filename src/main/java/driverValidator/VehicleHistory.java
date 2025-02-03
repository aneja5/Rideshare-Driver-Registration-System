package option2;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the history of a vehicle, including incidents and violations.
 */
public class VehicleHistory {
    private LocalDate date;
    private Enum<?> violationType;
    private CrashType crashType;
    private Person driverName;

    /**
     * Constructs a VehicleHistory object with the given details.
     *
     * @param date The date of the incident or violation.
     * @param driverName The name of the driver involved in the incident or violation.
     * @param violationType The type of violation, if any.
     * @param crashType The type of crash, if any.
     */
    public VehicleHistory(LocalDate date, Person driverName, Enum<?> violationType, CrashType crashType) {
        this.date = date;
        this.driverName = driverName;
        this.violationType = violationType;
        this.crashType = crashType;
    }

    /**
     * Gets the date of the incident or violation.
     *
     * @return The date of the incident or violation.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the type of violation, if any.
     *
     * @return The type of violation, or null if there is none.
     */
    public Enum<?> getViolationType() {
        return violationType;
    }

    /**
     * Gets the type of crash, if any.
     *
     * @return The type of crash, or null if there is none.
     */
    public CrashType getCrashType() {
        return crashType;
    }

    /**
     * Gets the name of the driver involved in the incident or violation.
     *
     * @return The name of the driver involved.
     */
    public Person getDriverName() {
        return driverName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleHistory that = (VehicleHistory) o;
        return Objects.equals(date, that.date) && Objects.equals(violationType, that.violationType) &&
                crashType == that.crashType && Objects.equals(driverName, that.driverName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, violationType, crashType, driverName);
    }

    @Override
    public String toString() {
        return "VehicleHistory{" +
                "date=" + date +
                ", violationType=" + violationType +
                ", crashType=" + crashType +
                ", driverName=" + driverName +
                '}';
    }
}
