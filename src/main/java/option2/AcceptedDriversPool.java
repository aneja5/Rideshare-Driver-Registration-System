package option2;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a pool of accepted prospective drivers.
 */
public class AcceptedDriversPool {
    private Set<ProspectiveDriver> acceptedProspectiveDrivers;

    /**
     * Constructs an empty AcceptedDriversPool.
     */
    public AcceptedDriversPool() {
        acceptedProspectiveDrivers = new HashSet<>();
    }

    /**
     * Adds a prospective driver to the pool, but only if no driver with the same name
     * and identical vehicles already exists.
     *
     * @param prospectiveDriver The prospective driver to add.
     */
    public void addDriver(ProspectiveDriver prospectiveDriver) {
        if (acceptedProspectiveDrivers.stream().anyMatch(existingDriver ->
                existingDriver.getName().equals(prospectiveDriver.getName()) &&
                        existingDriver.getVehicles().equals(prospectiveDriver.getVehicles()))) {
            System.out.println("Driver with same name and vehicles already exists.");
        } else {
            acceptedProspectiveDrivers.add(prospectiveDriver);
        }
    }

    /**
     * Provides information about drivers with a given last name.
     *
     * @param lastName The last name of the driver to search for.
     */
    public void provideDriverInfo(String lastName) {
        List<ProspectiveDriver> matchingProspectiveDrivers = acceptedProspectiveDrivers.stream()
                .filter(prospectiveDriver -> prospectiveDriver.getName().getLastName().equalsIgnoreCase(lastName))
                .sorted((d1, d2) -> d1.getName().getFirstName().compareTo(d2.getName().getFirstName()))
                .collect(Collectors.toList());

        if (matchingProspectiveDrivers.isEmpty()) {
            System.out.println("No registered driver found");
        } else {
            for (ProspectiveDriver prospectiveDriver : matchingProspectiveDrivers) {
                System.out.println(prospectiveDriver.getName().getFirstName() + " " + prospectiveDriver.getName().getLastName());
                for (Vehicle vehicle : prospectiveDriver.getVehicles()) {
                    System.out.println(vehicle.toString());
                }
                if (!prospectiveDriver.getDriverHistory().isEmpty()) {
                    System.out.println("Driving violations:");
                    for (DriverHistory history : prospectiveDriver.getDriverHistory()) {
                        System.out.println(history.toString());
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptedDriversPool that = (AcceptedDriversPool) o;
        return Objects.equals(acceptedProspectiveDrivers, that.acceptedProspectiveDrivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptedProspectiveDrivers);
    }

    @Override
    public String toString() {
        return "AcceptedDriversPool{" +
                "acceptedProspectiveDrivers=" + acceptedProspectiveDrivers +
                '}';
    }
}