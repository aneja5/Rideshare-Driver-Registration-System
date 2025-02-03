package option2;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents a driver's license.
 * It stores information about the driver, the issuing entity, and the validity of the license.
 */
public class DriversLicense{
    private static final int MIN_AGE = 21;
    private static final int MIN_MONTHS_FROM_ISSUANCE = 6;
    private Person driversNameOnLicense;
    private LocalDate dateOfBirthOnLicense;
    private String driverLicenseNumber;
    private Address driversAddress;
    private String countryOfIssuance;
    private String stateOfIssuance;
    private LocalDate issuanceDate;
    private LocalDate expirationDate;

    /**
     * Constructor to create a DriversLicense instance.
     *
     * @param driversNameOnLicense The name of the person as it appears on the license.
     * @param dateOfBirthOnLicense The date of birth of the person as it appears on the license.
     * @param driverLicenseNumber The unique identifier for the driver's license.
     * @param driversAddress The address of the driver as it appears on the license.
     * @param countryOfIssuance The country that issued the driver's license.
     * @param stateOfIssuance The state that issued the driver's license.
     * @param issuanceDate The date the driver's license was issued.
     * @param expirationDate The date the driver's license expires.
     */
    public DriversLicense(Person driversNameOnLicense, LocalDate dateOfBirthOnLicense, String driverLicenseNumber,
                          Address driversAddress ,String countryOfIssuance, String stateOfIssuance, LocalDate issuanceDate, LocalDate expirationDate) {
        this.driversNameOnLicense = driversNameOnLicense;
        this.dateOfBirthOnLicense = dateOfBirthOnLicense;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driversAddress = driversAddress;
        this.countryOfIssuance = countryOfIssuance;
        this.stateOfIssuance = stateOfIssuance;
        this.issuanceDate = issuanceDate;
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the date of birth of the person as it appears on the driver's license.
     *
     * @return The date of birth as a LocalDate object.
     */
    public LocalDate getDateOfBirthOnLicense() {
        return dateOfBirthOnLicense;
    }

    /**
     * Returns the unique identifier for the driver's license.
     *
     * @return The driver's license number as a String.
     */
    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    /**
     * Returns the address of the driver as it appears on the driver's license.
     *
     * @return The driver's address as an Address object.
     */
    public Address getDriversAddress() {
        return driversAddress;
    }

    /**
     * Returns the country that issued the driver's license.
     *
     * @return The country of issuance as a String.
     */
    public String getCountryOfIssuance() {
        return countryOfIssuance;
    }

    /**
     * Returns the state or province that issued the driver's license (applicable in some countries).
     *
     * @return The state or province of issuance as a String.
     */
    public String getStateOfIssuance() {
        return stateOfIssuance;
    }

    /**
     * Returns the date the driver's license was issued.
     *
     * @return The issuance date as a LocalDate object.
     */
    public LocalDate getIssuanceDate() {
        return issuanceDate;
    }

    /**
     * Returns the date the driver's license expires.
     *
     * @return The expiration date as a LocalDate object.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Checks if the provided name and date of birth match the information on the driver's license.
     *
     * @param driversName The name of the person to compare.
     * @param dateOfBirth The date of birth to compare.
     * @return True if the name and date of birth match, false otherwise.
     */
    public boolean isValidDriver(Person driversName, LocalDate dateOfBirth) {
        return (driversName.equals(driversNameOnLicense) && dateOfBirth.equals(dateOfBirthOnLicense));
    }

    /**
     * Checks if the driver meets the minimum age requirement to hold a valid license.
     *
     * @return True if the driver is of minimum age, false otherwise.
     */
    public boolean isValidAge() {
        return (this.dateOfBirthOnLicense.plusYears(MIN_AGE).isBefore(LocalDate.now()));
    }

    /**
     * Checks if the driver's license was issued by a valid country (US, USA, or CANADA).
     *
     * @return True if the country of issuance is valid, false otherwise.
     */
    public boolean isValidCountryOfIssuance() {
        return (this.countryOfIssuance.equals("US") || this.countryOfIssuance.equals("USA") ||
                this.countryOfIssuance.equals("CANADA"));
    }

    /**
     * Checks if the issuance date is more than 6 months ago
     *
     * @return True is issuance date is valid, false otherwise
     */
    public boolean isValidIssuanceDate() {
        return (this.issuanceDate.plusMonths(MIN_MONTHS_FROM_ISSUANCE).isBefore(LocalDate.now()));
    }

    /**
     * Checks if license is valid
     *
     * @return True if the expiration date is valid, false otherwise.
     */
    public boolean isValidExpirationDate() {
        return (this.expirationDate.isAfter(LocalDate.now()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriversLicense that = (DriversLicense) o;
        return Objects.equals(driversNameOnLicense, that.driversNameOnLicense) && Objects.equals(dateOfBirthOnLicense, that.dateOfBirthOnLicense) && Objects.equals(driverLicenseNumber, that.driverLicenseNumber) && Objects.equals(driversAddress, that.driversAddress) && Objects.equals(countryOfIssuance, that.countryOfIssuance) && Objects.equals(stateOfIssuance, that.stateOfIssuance) && Objects.equals(issuanceDate, that.issuanceDate) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driversNameOnLicense, dateOfBirthOnLicense, driverLicenseNumber, driversAddress, countryOfIssuance, stateOfIssuance, issuanceDate, expirationDate);
    }

    @Override
    public String toString() {
        return "DriversLicense{" +
                "driversNameOnLicense=" + driversNameOnLicense +
                ", dateOfBirthOnLicense=" + dateOfBirthOnLicense +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", driversAddress=" + driversAddress +
                ", countryOfIssuance='" + countryOfIssuance + '\'' +
                ", stateOfIssuance='" + stateOfIssuance + '\'' +
                ", issuanceDate=" + issuanceDate +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
