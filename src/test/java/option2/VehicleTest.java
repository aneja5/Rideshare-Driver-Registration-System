package option2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

class VehicleTest {
    private Vehicle vehicle;
    private Person owner;
    private Person driverInvolved;
    private Insurance insurance;
    private VehicleHistory history;

    @BeforeEach
    void setUp() {
        owner = new Person("Jane","Doe" , LocalDate.of(1985, 12, 15));
        vehicle = new Vehicle("Ford", "Mustang", 2020, "Blue", "ABCD1234", owner);
        insurance = new Insurance(owner, null, LocalDate.of(2025, 1, 1));
        driverInvolved = new Person("Jane", "Doe");
        history = new VehicleHistory(LocalDate.of(2021, 12, 22), driverInvolved,
                null, CrashType.FENDER_BENDER);
    }

    @Test
    void getMake() {
        assertEquals("Ford", vehicle.getMake());
    }

    @Test
    void getModel() {
        assertEquals("Mustang", vehicle.getModel());
    }

    @Test
    void getYear() {
        assertEquals(2020, vehicle.getYear());
    }

    @Test
    void getColor() {
        assertEquals("Blue", vehicle.getColor());
    }

    @Test
    void getLicensePlateNumber() {
        assertEquals("ABCD1234", vehicle.getLicensePlateNumber());
    }

    @Test
    void getVehicleOwner() {
        assertEquals(owner, vehicle.getVehicleOwner());
    }

    @Test
    void getInsurance() {
        assertNull(vehicle.getInsurance());
        vehicle.setInsurance(insurance);
        assertEquals(insurance, vehicle.getInsurance());
    }

    @Test
    void getVehicleHistory() {
        assertTrue(vehicle.getVehicleHistory().isEmpty());
        vehicle.addVehicleHistory(history);
        assertEquals(List.of(history), vehicle.getVehicleHistory());
    }

    @Test
    void addVehicleHistory() {
        vehicle.addVehicleHistory(history);
        assertEquals(1, vehicle.getVehicleHistory().size());
        assertTrue(vehicle.getVehicleHistory().contains(history));
    }

    @Test
    void isValidVehicle() {
        assertTrue(vehicle.isValidVehicle());
        Vehicle oldVehicle = new Vehicle("Ford", "Model T", LocalDate.now().getYear() - 15,
                "Black", "OLD123", owner);
        assertFalse(oldVehicle.isValidVehicle());
    }

    @Test
    void isVehicleOwnerAndInsuranceOwnerNameSame() {
        vehicle.setInsurance(insurance);
        assertTrue(vehicle.isVehicleOwnerAndInsuranceOwnerNameSame());

        Insurance wrongInsurance = new Insurance(new Person("John", "Doe",
                LocalDate.of(1990, 5, 5)), null, LocalDate.of(2025,
                1, 1));
        vehicle.setInsurance(wrongInsurance);
        assertFalse(vehicle.isVehicleOwnerAndInsuranceOwnerNameSame());
    }

    @Test
    void setInsurance() {
        assertNull(vehicle.getInsurance());
        vehicle.setInsurance(insurance);
        assertNotNull(vehicle.getInsurance());
        assertEquals(insurance, vehicle.getInsurance());
    }

    @Test
    void testEquals() {
        Vehicle sameVehicle = new Vehicle("Ford", "Mustang", 2020, "Blue",
                "ABCD1234", owner);
        assertEquals(vehicle, sameVehicle);

        Vehicle differentVehicle = new Vehicle("Toyota", "Corolla", 2020, "White",
                "XYZ9876", owner);
        assertNotEquals(vehicle, differentVehicle);
    }

    @Test
    void testHashCode() {
        Vehicle sameVehicle = new Vehicle("Ford", "Mustang", 2020, "Blue",
                "ABCD1234", owner);
        assertEquals(vehicle.hashCode(), sameVehicle.hashCode());

        Vehicle differentVehicle = new Vehicle("Toyota", "Corolla", 2020, "White",
                "XYZ9876", owner);
        assertNotEquals(vehicle.hashCode(), differentVehicle.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("2020 Blue Ford Mustang, ABCD1234", vehicle.toString());
    }
}
