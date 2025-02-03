package option2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

class ProspectiveDriverTest {
    private ProspectiveDriver driver;
    private Person person;
    private DriversLicense license;
    private Vehicle vehicle;
    private DriverHistory history;

    @BeforeEach
    void setUp() {
        person = new Person("John","Doe" , LocalDate.of(1990, 12, 10));
        license = new DriversLicense(person, LocalDate.of(1990, 1, 1),
                "D1234567", new Address("123 Elm St", "Springfield",
                "IL", "62704"), "USA", "IL",
                LocalDate.of(2015, 5, 1), LocalDate.of(2025, 5, 1));
        driver = new ProspectiveDriver(person, license);
        vehicle = new Vehicle("Toyota", "Camry", 2010, "Red", "XYZ123", person);
        history = new DriverHistory(LocalDate.now(), ViolationType.SPEEDING);
    }

    @Test
    void getName() {
        assertEquals(person, driver.getName());
    }

    @Test
    void getLicense() {
        assertEquals(license, driver.getLicense());
    }

    @Test
    void getVehicles() {
        assertTrue(driver.getVehicles().isEmpty());
        driver.addVehicle(vehicle);
        assertEquals(List.of(vehicle), driver.getVehicles());
    }

    @Test
    void getDriverHistory() {
        assertTrue(driver.getDriverHistory().isEmpty());
        driver.addDriverHistory(history);
        assertEquals(List.of(history), driver.getDriverHistory());
    }

    @Test
    void addDriverHistory() {
        driver.addDriverHistory(history);
        assertEquals(1, driver.getDriverHistory().size());
        assertTrue(driver.getDriverHistory().contains(history));
    }

    @Test
    void addVehicle() {
        driver.addVehicle(vehicle);
        assertEquals(1, driver.getVehicles().size());
        assertTrue(driver.getVehicles().contains(vehicle));
    }

    @Test
    void testEquals() {
        ProspectiveDriver sameDriver = new ProspectiveDriver(person, license);
        sameDriver.addVehicle(vehicle);
        sameDriver.addDriverHistory(history);

        driver.addVehicle(vehicle);
        driver.addDriverHistory(history);

        assertEquals(driver, sameDriver);
    }

    @Test
    void testHashCode() {
        ProspectiveDriver sameDriver = new ProspectiveDriver(person, license);
        assertEquals(driver.hashCode(), sameDriver.hashCode());

        driver.addVehicle(vehicle);
        assertNotEquals(driver.hashCode(), sameDriver.hashCode());
    }

    @Test
    void testToString() {
        String expected = "ProspectiveDriver{name=" + person +
                ", license=" + license +
                ", vehicles=" + driver.getVehicles() +
                ", driverHistory=" + driver.getDriverHistory() +
                '}';
        assertEquals(expected, driver.toString());
    }
}
