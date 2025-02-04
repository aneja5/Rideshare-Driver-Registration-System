package option2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DriversLicenseTest {
    private DriversLicense license;
    private Person person;
    private Address address;

    @BeforeEach
    void setUp() {
        person = new Person("John", "Doe"); // Assuming Person constructor: Person(String name, LocalDate dateOfBirth)
        address = new Address("123 Main St", "Anytown", "Anystate", "12345");
        license = new DriversLicense(person, LocalDate.of(2000, 1, 1), "D123456789",
                address, "USA", "CA", LocalDate.of(2015, 1, 1), LocalDate.of(2025, 1, 1));
    }

    @Test
    void getDateOfBirthOnLicense() {
        assertEquals(LocalDate.of(2000, 1, 1), license.getDateOfBirthOnLicense(), "Date of birth should match initialization.");
    }

    @Test
    void getDriverLicenseNumber() {
        assertEquals("D123456789", license.getDriverLicenseNumber(), "License number should match initialization.");
    }

    @Test
    void getDriversAddress() {
        assertEquals(address, license.getDriversAddress(), "Address should match initialization.");
    }

    @Test
    void getCountryOfIssuance() {
        assertEquals("USA", license.getCountryOfIssuance(), "Country of issuance should match initialization.");
    }

    @Test
    void getStateOfIssuance() {
        assertEquals("CA", license.getStateOfIssuance(), "State of issuance should match initialization.");
    }

    @Test
    void getIssuanceDate() {
        assertEquals(LocalDate.of(2015, 1, 1), license.getIssuanceDate(), "Issuance date should match initialization.");
    }

    @Test
    void getExpirationDate() {
        assertEquals(LocalDate.of(2025, 1, 1), license.getExpirationDate(), "Expiration date should match initialization.");
    }

    @Test
    void isValidDriver() {
        assertTrue(license.isValidDriver(new Person("John", "Doe"), LocalDate.of(2000, 1, 1)));
        assertFalse(license.isValidDriver(new Person("Jane", "Doe"), LocalDate.of(2000, 1, 1)));
    }

    @Test
    void isValidAge() {
        assertTrue(license.isValidAge());
    }

    @Test
    void isValidCountryOfIssuance() {
        assertTrue(license.isValidCountryOfIssuance());
        license = new DriversLicense(person, LocalDate.of(2000, 1, 1), "D123456789",
                address, "InvalidCountry", "CA", LocalDate.of(2015, 1, 1), LocalDate.of(2025, 1, 1));
        assertFalse(license.isValidCountryOfIssuance());
    }

    @Test
    void isValidIssuanceDate() {
        assertTrue(license.isValidIssuanceDate());
        license = new DriversLicense(person, LocalDate.of(2000, 1, 1), "D123456789",
                address, "USA", "CA", LocalDate.now(), LocalDate.of(2025, 1, 1));
        assertFalse(license.isValidIssuanceDate());
    }

    @Test
    void isValidExpirationDate() {
        assertTrue(license.isValidExpirationDate());
        license = new DriversLicense(person, LocalDate.of(2000, 1, 1), "D123456789",
                address, "USA", "CA", LocalDate.of(2015, 1, 1), LocalDate.now().minusDays(1));
        assertFalse(license.isValidExpirationDate());
    }

    @Test
    void testEquals() {
        DriversLicense anotherLicense = new DriversLicense(person, LocalDate.of(2000, 1, 1), "D123456789",
                address, "USA", "CA", LocalDate.of(2015, 1, 1), LocalDate.of(2025, 1, 1));
        assertEquals(license, anotherLicense);
    }

    @Test
    void testHashCode() {
        DriversLicense anotherLicense = new DriversLicense(person, LocalDate.of(2000, 1, 1), "D123456789",
                address, "USA", "CA", LocalDate.of(2015, 1, 1), LocalDate.of(2025, 1, 1));
        assertEquals(license.hashCode(), anotherLicense.hashCode());
    }
}
