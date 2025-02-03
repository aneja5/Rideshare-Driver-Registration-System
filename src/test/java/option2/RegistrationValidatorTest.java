package option2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationValidatorTest {
    private ProspectiveDriver prospectiveDriver;
    private Person driverInfo;
    private DriversLicense license;
    private Vehicle vehicle;
    private Insurance insurance;

    @BeforeEach
    void setUp() {
        driverInfo = new Person("John", "Doe");
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);
        license = new DriversLicense(driverInfo, dateOfBirth, "123456",
                new Address("1234 Elm Street", "Townsville", "CA", "12345"),
                "USA", "CA", LocalDate.now().minusYears(1),
                LocalDate.now().plusYears(5));
        prospectiveDriver = new ProspectiveDriver(driverInfo, license);


        vehicle = new Vehicle("Toyota", "Corolla", 2010, "Blue",
                "ABC123", driverInfo);
        insurance = new Insurance(driverInfo, null, LocalDate.now().minusMonths(1));
        vehicle.setInsurance(insurance);

        prospectiveDriver.addVehicle(vehicle);
    }

    @Test
    void testValidateDriverLicense_ValidLicense() {
        assertTrue(RegistrationValidator.validateDriverLicense(license, driverInfo, LocalDate.of(2000, 1, 1)));
    }

    @Test
    void testValidateDriverLicense_ExpiredLicense() {
        DriversLicense expiredLicense = new DriversLicense(driverInfo, LocalDate.of(2000, 1, 1), "123456", new Address("1234 Elm Street", "Townsville", "CA", "12345"),
                "USA", "CA", LocalDate.now().minusYears(1), LocalDate.now().minusDays(1));
        assertFalse(RegistrationValidator.validateDriverLicense(expiredLicense, driverInfo, LocalDate.of(2000, 1, 1)));
    }

    @Test
    void testValidateVehicle_ValidVehicle() {
        assertFalse(RegistrationValidator.validateVehicle(vehicle));
    }

    @Test
    void testValidateVehicle_InvalidInsurance() {
        Insurance invalidInsurance = new Insurance(new Person("Fake", "Name"), null, LocalDate.now().minusMonths(1));
        vehicle.setInsurance(invalidInsurance);
        assertFalse(RegistrationValidator.validateVehicle(vehicle));
    }

    @Test
    void testValidateRegistration_ValidData() {
        assertFalse(RegistrationValidator.validateRegistration(driverInfo, LocalDate.of(2000, 1, 1), prospectiveDriver));
    }

    @Test
    void testValidateRegistration_InvalidDriver() {
        ProspectiveDriver invalidDriver = new ProspectiveDriver(new Person("Invalid", "Driver"), license);
        assertFalse(RegistrationValidator.validateRegistration(new Person("Invalid", "Driver"), LocalDate.of(2000, 1, 1), invalidDriver));
    }
}
