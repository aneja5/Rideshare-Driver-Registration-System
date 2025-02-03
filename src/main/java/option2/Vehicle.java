package option2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a vehicle with its make, model, year, color, license plate number, owner, insurance, and history.
 */
public class Vehicle {
    private static final int MAXIMUM_CAR_AGE = 15;
    private String make;
    private String model;
    private int year;
    private String color;
    private String licensePlateNumber;
    private Person vehicleOwner;
    private Insurance insurance;
    private List<VehicleHistory> vehicleHistory;

    /**
     * Constructs a Vehicle object with the given details.
     *
     * @param make The make of the vehicle.
     * @param model The model of the vehicle.
     * @param year The year of manufacture of the vehicle.
     * @param color The color of the vehicle.
     * @param licensePlateNumber The license plate number of the vehicle.
     * @param vehicleOwner The owner of the vehicle.
     */
    public Vehicle(String make, String model, int year, String color, String licensePlateNumber,
                   Person vehicleOwner) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleOwner = vehicleOwner;
        this.insurance = null;
        this.vehicleHistory = new ArrayList<>();

    }

    /**
     * Gets the make of the vehicle.
     *
     * @return The make of the vehicle.
     */
    public String getMake() {
        return make;
    }

    /**
     * Gets the model of the vehicle.
     *
     * @return The model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the year of manufacture of the vehicle.
     *
     * @return The year of manufacture of the vehicle.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the color of the vehicle.
     *
     * @return The color of the vehicle.
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the license plate number of the vehicle.
     *
     * @return The license plate number of the vehicle.
     */
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    /**
     * Gets the owner of the vehicle.
     *
     * @return The owner of the vehicle.
     */
    public Person getVehicleOwner() {
        return vehicleOwner;
    }

    /**
     * Gets the insurance details of the vehicle.
     *
     * @return The insurance details of the vehicle.
     */
    public Insurance getInsurance() {
        return insurance;
    }

    /**
     * Gets the history of the vehicle.
     *
     * @return The history of the vehicle.
     */
    public List<VehicleHistory> getVehicleHistory() {
        return vehicleHistory;
    }

    /**
     * Adds a vehicle history record for this vehicle.
     *
     * @param history The vehicle history record to add.
     */
    public void addVehicleHistory(VehicleHistory history) {
        vehicleHistory.add(history);
    }

    /**
     * Checks if the vehicle is valid based on its age.
     *
     * @return True if the vehicle is valid, false otherwise.
     */
    public boolean isValidVehicle() {
        return (LocalDate.now().getYear() - this.year < MAXIMUM_CAR_AGE);
    }

    /**
     * Checks if the vehicle owner's name matches the insurance owner's name.
     *
     * @return True if the names match, false otherwise.
     */
    public boolean isVehicleOwnerAndInsuranceOwnerNameSame() {
        return vehicleOwner.equals(insurance.getOwner());
    }

    /**
     * Sets the insurance details for the vehicle.
     *
     * @param insurance The insurance details to set.
     */
    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year && Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model) &&
                Objects.equals(color, vehicle.color) && Objects.equals(licensePlateNumber, vehicle.licensePlateNumber)
                && Objects.equals(vehicleOwner, vehicle.vehicleOwner) && Objects.equals(insurance, vehicle.insurance)
                && Objects.equals(vehicleHistory, vehicle.vehicleHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, color, licensePlateNumber, vehicleOwner, insurance, vehicleHistory);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s, %s", year, color, make + " " + model, licensePlateNumber);
    }
}