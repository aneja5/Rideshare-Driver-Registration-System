package option2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents an insurance policy. It holds information about the owner,
 * additional drivers, and the expiration date of the policy.
 */
public class Insurance {

    private Person owner;
    private Person[] additionalDrivers;
    private LocalDate expirationDate;

    /**
     * Constructor to create a new Insurance object.
     *
     * @param owner             The person who owns the insurance policy.
     * @param additionalDrivers An array of additional drivers covered by the policy (can be null).
     * @param expirationDate     The date when the insurance policy expires.
     */
    public Insurance(Person owner, Person[] additionalDrivers, LocalDate expirationDate) {
        this.owner = owner;
        this.additionalDrivers = additionalDrivers;
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the owner of the insurance policy.
     *
     * @return The person who owns the insurance policy.
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Gets an array of additional drivers covered by the insurance policy.
     *
     * @return An array of additional drivers (can be null).
     */
    public Person[] getAdditionalDrivers() {
        return additionalDrivers;
    }

    /**
     * Gets the expiration date of the insurance policy.
     *
     * @return The date when the insurance policy expires.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Checks if the insurance policy is not expired.
     *
     * @return True if the expiration date is after the current date, false otherwise.
     */
    public boolean isInsuranceValid() {
        return (this.expirationDate.isAfter(LocalDate.now()));
    }

    /**
     * Checks if the provided person is both the owner and a listed driver on the policy.
     *
     * @param driversName The person to check.
     * @return True if the person is both the owner and listed as an additional driver, false otherwise.
     */
    public boolean isDriverAndOwnerSame(Person driversName) {
        return (driversName.equals(getOwner()));
    }

    /**
     * Checks if the provided person is listed as an additional driver on the policy.
     *
     * @param driversName The person to check.
     * @return True if the person is found in the list of additional drivers, false otherwise.
     */
    public boolean isDriverInAdditionalDriversList(Person driversName) {
        if (additionalDrivers != null) {
            for (Person driver : additionalDrivers) {
                if (driver.equals(driversName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insurance insurance = (Insurance) o;
        return Objects.equals(owner, insurance.owner) && Arrays.equals(additionalDrivers, insurance.additionalDrivers) && Objects.equals(expirationDate, insurance.expirationDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(owner, expirationDate);
        result = 31 * result + Arrays.hashCode(additionalDrivers);
        return result;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "owner=" + owner +
                ", additionalDrivers=" + Arrays.toString(additionalDrivers) +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
