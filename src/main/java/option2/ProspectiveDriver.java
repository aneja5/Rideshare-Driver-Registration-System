package option2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a prospective driver with a person's information, driver's license, vehicles, and driver history.
 */
public class ProspectiveDriver {
    private Person name;
    private DriversLicense license;
    private List<Vehicle> vehicles;
    private List<DriverHistory> driverHistory;

    /**
     * Constructs a ProspectiveDriver with the given person's information and driver's license.
     *
     * @param name The person's information.
     * @param license The driver's license.
     */
    public ProspectiveDriver(Person name, DriversLicense license) {
        this.name = name;
        this.license = license;
        this.vehicles = new ArrayList<>();
        this.driverHistory = new ArrayList<>();
    }

    /**
     * Gets the person's information.
     *
     * @return The person's information.
     */
    public Person getName() {
        return name;
    }

    /**
     * Gets the driver's license.
     *
     * @return The driver's license.
     */
    public DriversLicense getLicense() {
        return license;
    }

    /**
     * Gets the list of vehicles associated with this prospective driver.
     *
     * @return The list of vehicles.
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Gets the driver history of this prospective driver.
     *
     * @return The list of driver history records.
     */
    public List<DriverHistory> getDriverHistory() {
        return driverHistory;
    }

    /**
     * Adds a driver history record for this prospective driver.
     *
     * @param history The driver history record to add.
     */
    public void addDriverHistory(DriverHistory history) {
        driverHistory.add(history);
    }

    /**
     * Adds a vehicle to the list of vehicles associated with this prospective driver.
     *
     * @param vehicle The vehicle to add.
     */
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProspectiveDriver that = (ProspectiveDriver) o;
        return Objects.equals(name, that.name) && Objects.equals(license, that.license) && Objects.equals(vehicles, that.vehicles) && Objects.equals(driverHistory, that.driverHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, license, vehicles, driverHistory);
    }

    @Override
    public String toString() {
        return "ProspectiveDriver{" +
                "name=" + name +
                ", license=" + license +
                ", vehicles=" + vehicles +
                ", driverHistory=" + driverHistory +
                '}';
    }
}