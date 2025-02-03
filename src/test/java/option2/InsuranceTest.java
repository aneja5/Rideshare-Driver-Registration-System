package option2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class InsuranceTest {
    private Insurance insurance;
    private Person owner;
    private Person additionalDriver1;
    private Person additionalDriver2;

    @BeforeEach
    void setUp() {
        owner = new Person("John", "Doe");
        additionalDriver1 = new Person("Jane", "Smith");
        additionalDriver2 = new Person("Jake", "Johnson");
        Person[] additionalDrivers = {additionalDriver1, additionalDriver2};
        insurance = new Insurance(owner, additionalDrivers, LocalDate.now().plusYears(1));
    }

    @Test
    void getOwner() {
        assertEquals(owner, insurance.getOwner());
    }

    @Test
    void getAdditionalDrivers() {
        assertNotNull(insurance.getAdditionalDrivers());
        assertEquals(2, insurance.getAdditionalDrivers().length);
    }

    @Test
    void getExpirationDate() {
        assertEquals(LocalDate.now().plusYears(1), insurance.getExpirationDate());
    }

    @Test
    void isInsuranceValid() {
        assertTrue(insurance.isInsuranceValid());
        insurance = new Insurance(owner, null, LocalDate.now().minusDays(1));
        assertFalse(insurance.isInsuranceValid());
    }

    @Test
    void isDriverAndOwnerSame() {
        assertTrue(insurance.isDriverAndOwnerSame(new Person("John", "Doe")));
        assertFalse(insurance.isDriverAndOwnerSame(additionalDriver1));
    }

    @Test
    void isDriverInAdditionalDriversList() {
        assertTrue(insurance.isDriverInAdditionalDriversList(additionalDriver1));
        assertFalse(insurance.isDriverInAdditionalDriversList(new Person("Random", "Person")));
    }

    @Test
    void testEquals() {
        Insurance sameInsurance = new Insurance(owner, new Person[]{additionalDriver1, additionalDriver2}, LocalDate.now().plusYears(1));
        assertEquals(insurance, sameInsurance);

        Insurance differentInsurance = new Insurance(new Person("Random", "Person"), null, LocalDate.now().plusYears(1));
        assertNotEquals(insurance, differentInsurance);
    }

}
